package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.gateway.rest.MangaBitClient;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.PageMangaBit;
import com.mangajj.mangacontrol.services.MangaBitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MangaBitServiceImpl implements MangaBitService {

    @Autowired
    private MangaBitClient mangaBitClient;

    @Override
    public List<ChapterMangaBit> getChapters(Long mangaId) {
        log.info("Get Chapters by Id {}", mangaId);
        int totalPages = 1;
        int page = 1;
        List<ChapterMangaBit> chapterList = new ArrayList<>();

        do {
            try {
                var chapters = mangaBitClient.getChapter(mangaId, page);
                chapterList.addAll(chapters.getChapters());
                totalPages = chapters.getPages();
                page++;
            } catch (Exception e) {
                log.error("Erro to get chapter by Id {} - ", mangaId, e);
                break;
            }
        } while (page != totalPages);

        return chapterList;
    }

    @Override
    public List<PageMangaBit> getPages(Long mangaId, String chapterId) {
        int totalPages = 1;
        int page = 1;
        List<PageMangaBit> pageList = new ArrayList<>();

        do {
            try {
                var chapters = mangaBitClient.getChapterPages(mangaId, chapterId);
                pageList.addAll(chapters.getData());
                totalPages = chapters.getTotalPages();
                page++;
            } catch (Exception e) {
                log.error("Erro to get pages by Id {} - ", mangaId, e);
                break;
            }
        } while (page != totalPages);

        return pageList;
    }

}
