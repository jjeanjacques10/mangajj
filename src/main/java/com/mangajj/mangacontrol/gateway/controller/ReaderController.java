package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.services.MangaBitService;
import com.mangajj.mangacontrol.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/read")
public class ReaderController {

    @Autowired
    private MangaBitService mangaBitService;

    @Autowired
    private MangaService mangaService;

    @GetMapping("/{idManga}")
    public ResponseEntity getChapters(@PathVariable Long idManga,
                                      @RequestParam(defaultValue = "0") int chapters_page) {
        var manga = mangaService.getById(idManga);
        var chapter = mangaBitService.getChapters(idManga, manga.getTitle(), chapters_page);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/{idManga}/chapter/{idChapter}")
    public ResponseEntity getChapters(@PathVariable Long idManga,
                                      @PathVariable String idChapter) {
        var pages = mangaBitService.getPages(idManga, idChapter);
        return ResponseEntity.ok(pages);
    }
}
