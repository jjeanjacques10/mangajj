package com.mangajj.mangacontrol.gateway.rest;

import com.mangajj.mangacontrol.entities.MangaEntity;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MangaDataContract;
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

    @GetMapping("/{id}")
    public ResponseEntity<MangaEntity> getMangaById(@PathVariable Long id) {
        var manga = service.getById(id);
        return ResponseEntity.ok(manga);
    }

    @PostMapping("/")
    public ResponseEntity<String> createManga(@RequestBody MangaDataContract manga) {
        service.createManga(manga);
        return ResponseEntity.ok().body("created manga: " + manga.getTitle());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<String>> createBatchManga(@RequestBody List<MangaDataContract> mangas) {
        service.createBatch(mangas);
        var titlesCreated = mangas.stream().map(MangaDataContract::getTitle).collect(Collectors.toList());
        return ResponseEntity.ok().body(titlesCreated);
    }

}
