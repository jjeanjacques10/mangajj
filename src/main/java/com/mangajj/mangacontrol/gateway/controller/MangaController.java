package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.services.MangaBitService;
import com.mangajj.mangacontrol.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService service;

    @Autowired
    private MangaBitService mangaBitService;

    @GetMapping("/")
    public ResponseEntity<List<MangaEntity>> getManga() {
        var mangaList = service.getAllMangas();
        return ResponseEntity.ok().body(mangaList);
    }

    @GetMapping
    public ResponseEntity getMangaByTitle(@RequestParam String title) {
        try {
            var mangaList = service.getByTitle(title);
            return ResponseEntity.ok().body(mangaList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getMangaById(@PathVariable Long id,
                                       @RequestParam(defaultValue = "false") boolean expanded_content) {
        try {
            List<ChapterMangaBit> chapters = new ArrayList<>();
            var manga = service.getById(id);

            if (expanded_content) {
                chapters = mangaBitService.getChapters(id);
            }

            var mangaDTO = MangaDTO.builder()
                    .id(manga.getId())
                    .title(manga.getTitle())
                    .status(manga.getStatus())
                    .volumes(manga.getVolumes())
                    .imageUrl(manga.getImageUrl())
                    .synopsis(manga.getSynopsis())
                    .chapters(manga.getChapters())
                    .chaptersList(chapters)
                    .build();

            return ResponseEntity.ok(mangaDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> createManga(@Validated @RequestBody MangaDTO manga) {
        service.createManga(manga);
        return ResponseEntity.ok().body("created manga: " + manga.getTitle());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<String>> createBatchManga(@RequestBody List<MangaDTO> mangas) {
        service.createBatch(mangas);
        var titlesCreated = mangas.stream().map(MangaDTO::getTitle).collect(Collectors.toList());
        return ResponseEntity.ok().body(titlesCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteManga(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
