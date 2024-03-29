package com.mangajj.mangacontrol.adapter.web;

import com.mangajj.mangacontrol.adapter.persistence.entity.MangaEntity;
import com.mangajj.mangacontrol.adapter.web.dto.MangaDTO;
import com.mangajj.mangacontrol.adapter.web.dto.ResponseDTO;
import com.mangajj.mangacontrol.application.ChapterService;
import com.mangajj.mangacontrol.application.MangaService;
import com.mangajj.mangacontrol.utils.MetricUtils;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manga")
public class MangaController {

    private final MangaService service;
    private final ChapterService chapterService;
    private final ModelMapper mapper;
    private final MetricUtils metric;
    private final MeterRegistry registry;

    @Counted(value = "manga.title.requests", description = "Counts the number of requests with the same manga title")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getManga(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int limit,
            @RequestParam(required = false) String title
    ) {
        Map<String, Object> response = new HashMap<>();
        List<MangaEntity> mangas;

        if (title != null && !title.equals("")) {
            MetricUtils.processMangaRequest(registry, title).increment();
            mangas = service.getByTitle(title);
            response.put("last_page", 1);
        } else {
            if (limit == 0) limit = Integer.MAX_VALUE;

            Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "popularity"));
            var mangaPage = service.getAllMangas(pageable);

            mangas = mangaPage.getContent();
            response.put("last_page", mangaPage.getTotalPages());
        }
        response.put("data", mangas);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getMangaById(@PathVariable Long id, @RequestParam(defaultValue = "0", name = "chapters_page") int chaptersPage, @RequestParam(defaultValue = "false", name = "expanded_content") boolean expandedContent) {
        var manga = service.getById(id);
        MetricUtils.processMangaRequest(registry, manga.getTitle()).increment();
        var mangaDTO = mapper.map(manga, MangaDTO.class);
        return ResponseEntity.ok(ResponseDTO.builder().data(mangaDTO).build());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createManga(@Validated @RequestBody MangaDTO manga) {
        service.createManga(manga);
        return ResponseEntity.ok().body(ResponseDTO.builder().data("created manga: " + manga.getTitle()).build());
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseDTO> createBatchManga(@RequestBody List<MangaDTO> mangas) {
        service.createBatch(mangas);
        var titlesCreated = mangas.stream().map(MangaDTO::getTitle).collect(Collectors.toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(titlesCreated).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteManga(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
