package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.gateway.rest.MangaBitClient;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterListMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterRequestDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.PageMangaBit;
import com.mangajj.mangacontrol.services.MangaBitService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = {"pages-manga"})
public class MangaBitServiceImpl implements MangaBitService {

    @Autowired
    private MangaBitClient mangaBitClient;

    @Override
    public ChapterListMangaBit getChapters(Long mangaId, String title, int page) {
        log.info("Get Chapters by Id {}", mangaId);
        ChapterListMangaBit chapterList = new ChapterListMangaBit();

        try {
            var chaptersMangaBit = mangaBitClient.getChapter(mangaId, page);
            chapterList.setLastPage(chaptersMangaBit.getPages());
            chapterList.setChapterList(chaptersMangaBit.getChapters());
        } catch (FeignException e) {
            if (!e.getMessage().toLowerCase().contains("read timed out"))
                this.requestChapters(mangaId, title);
        } catch (Exception e) {
            log.error("Erro to get chapter by Id {} - ", mangaId, e);
        }

        return chapterList;
    }

    private void requestChapters(Long mangaId, String title) {
        try {
            log.info("Start scrap to manga {} {}", mangaId, title);
            var chapterRequested = ChapterRequestDataContract.builder().mangaId(mangaId).title(title).build();
            var response = mangaBitClient.requestNewChapters(chapterRequested);
            log.info("{} - Manga {} {} ", response.getMessage(), mangaId, title);
        } catch (Exception e) {
            log.error("Erro to request manga {} chapters - {}", title, e);
        }
    }

    @Override
    @Cacheable
    public List<PageMangaBit> getPages(Long mangaId, String chapterId) {
        log.info("Get pages by Id {} chapter {}", mangaId, chapterId);
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
