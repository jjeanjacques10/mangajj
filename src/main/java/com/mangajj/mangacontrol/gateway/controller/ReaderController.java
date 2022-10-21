package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.services.ChapterService;
import com.mangajj.mangacontrol.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/read")
public class ReaderController {

    @Autowired
    private ChapterService mangaBitService;

    @Autowired
    private MangaService mangaService;

    @GetMapping("/manga/{idManga}")
    public ResponseEntity getChapters(@PathVariable Long idManga) {
        var chapter = mangaBitService.getMangaChapters(idManga);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/chapter/{idChapter}")
    public ResponseEntity getChapter(@PathVariable Long idChapter) {
        var pages = mangaBitService.getChapter(idChapter);
        return ResponseEntity.ok(pages);
    }
}
