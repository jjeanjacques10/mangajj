package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.services.MangaBitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class ReaderController {

    @Autowired
    private MangaBitService mangaBitService;

    @GetMapping("/{idManga}")
    public ResponseEntity getChapters(@PathVariable Long idManga) {
        var chapter = mangaBitService.getChapters(idManga);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/{idManga}/chapter/{idChapter}")
    public ResponseEntity getChapters(@PathVariable Long idManga,
                                      @PathVariable String idChapter) {
        var pages = mangaBitService.getPages(idManga, idChapter);
        return ResponseEntity.ok(pages);
    }
}
