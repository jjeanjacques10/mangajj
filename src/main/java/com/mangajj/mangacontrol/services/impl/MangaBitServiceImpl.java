package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.gateway.rest.MangaBitClient;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterRequestDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.PageMangaBit;
import com.mangajj.mangacontrol.services.MangaBitService;
import feign.FeignException;
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
    public List<ChapterMangaBit> getChapters(Long mangaId, String title) {
        log.info("Get Chapters by Id {}", mangaId);
        int totalPages;
        int page = 0;
        List<ChapterMangaBit> chapterList = new ArrayList<>();

        do {
            try {
                var chapters = mangaBitClient.getChapter(mangaId, page);
                chapterList.addAll(chapters.getChapters());
                totalPages = chapters.getPages();
                page++;
            } catch (FeignException e) {
                if (!e.getMessage().contains("Read timed out")) {
                    this.requestChapters(mangaId, title);
                }
                break;
            } catch (Exception e) {
                log.error("Erro to get chapter by Id {} - ", mangaId, e);
                break;
            }
        } while (page != totalPages);

        return chapterList;
    }

    private void requestChapters(Long mangaId, String title) {
        try {
            log.info("Start scrap to manga {} {}", mangaId, title);
            var chapterRequested = ChapterRequestDataContract.builder().mangaId(mangaId).title(title).build();
            var response = mangaBitClient.requestNewChapters(chapterRequested);
            log.info("{} - Manga {} {} ", response.getMessage(), mangaId, title);
        } catch (Exception e) {
            log.error("Erro to request chapters - {}", e);
        }
    }

    @Override
    public List<PageMangaBit> getPages(Long mangaId, String chapterId) {
        List<PageMangaBit> pageList = new ArrayList<>();

        try {
            var chapters = mangaBitClient.getChapterPages(mangaId, chapterId);
            pageList.addAll(chapters.getData());
        } catch (Exception e) {
            log.error("Erro to get pages by Id {} - ", mangaId, e);
        }

        return pageList;
    }

}
