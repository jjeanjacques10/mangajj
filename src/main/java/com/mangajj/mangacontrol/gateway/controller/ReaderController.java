package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.gateway.controller.dto.ChapterDTO;
import com.mangajj.mangacontrol.services.ChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/read")
public class ReaderController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/manga/{idManga}")
    public ResponseEntity<List<ChapterDTO>> getChapters(@PathVariable Long idManga) {
        var chapters = chapterService.getMangaChapters(idManga);
        return ResponseEntity.ok(chapters.stream().map(c -> mapper.map(c, ChapterDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/manga/{mangaId}/chapter/{chapterNumber}")
    public ResponseEntity<ChapterDTO> getChapter(@PathVariable Long mangaId, @PathVariable String chapterNumber) {
        var chapter = chapterService.getChapter(mangaId, chapterNumber);
        return ResponseEntity.ok(mapper.map(chapter, ChapterDTO.class));
    }
}
