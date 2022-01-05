package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService service;

    @GetMapping("/")
    public ResponseEntity<List<MangaEntity>> getManga() {
        var mangaList = service.getAllMangas();
        return ResponseEntity.ok().body(mangaList);
    }

    @GetMapping
    public ResponseEntity<MangaEntity> getMangaByTitle(@RequestParam String title) {
        var mangaList = service.getByTitle(title);
        return ResponseEntity.ok().body(mangaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMangaById(@PathVariable Long id, @RequestHeader(defaultValue = "false") boolean cache) {
        try {
            var manga = service.getById(id);
            if (cache) {
                service.updateLazyLoad(manga);
            }
            return ResponseEntity.ok(manga);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> createManga(@RequestBody MangaDTO manga) {
        service.createManga(manga);
        return ResponseEntity.ok().body("created manga: " + manga.getTitle());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<String>> createBatchManga(@RequestBody List<MangaDTO> mangas) {
        service.createBatch(mangas);
        var titlesCreated = mangas.stream().map(MangaDTO::getTitle).collect(Collectors.toList());
        return ResponseEntity.ok().body(titlesCreated);
    }

}
