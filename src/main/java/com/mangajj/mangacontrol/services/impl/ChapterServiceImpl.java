package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.chapter.ChapterEntity;
import com.mangajj.mangacontrol.entity.chapter.MangaPageEntity;
import com.mangajj.mangacontrol.gateway.repositories.ChapterRepository;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.rest.MangaScrapperClient;
import com.mangajj.mangacontrol.services.ChapterService;
import com.mangajj.mangacontrol.shared.exception.ChapterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private MangaScrapperClient mangaScrapperClient;

    @Override
    public List<ChapterEntity> getMangaChapters(Long mangaId) {
        log.info("Get Chapters by Id {}", mangaId);

        try {
            return chapterRepository.findAllByMangaId(mangaId);
        } catch (Exception e) {
            log.error("Erro to get chapter by Id {} - ", mangaId, e);
            throw new ChapterNotFoundException("Erro to get chapter by Id " + mangaId + " - " + e.getMessage());
        }
    }

    @Override
    public ChapterEntity getChapter(Long mangaId, String number) {
        var chapter = chapterRepository.findChapterByMangaId(mangaId, number);

        if (chapter.isEmpty()) {
            chapter = requestChapter(mangaId, number);
        }

        return chapter.get();
    }

    private Optional<ChapterEntity> requestChapter(Long mangaId, String number) {
        try {
            var mangaTitle = mangaRepository.getById(mangaId).getTitle();
            var mangaScrapperResponse = mangaScrapperClient.getMangasMyList(
                    "manga_livre",
                    mangaTitle,
                    number
            );

            if (mangaScrapperResponse.getPages().isEmpty())
                throw new ChapterNotFoundException("Chapter id " + number + " not found");

            var chapter = Optional.of(ChapterEntity.builder()
                    .number(number)
                    .title("")
                    .mangaId(mangaId)
                    .releaseDate(LocalDateTime.now())
                    .pages(mangaScrapperResponse.getPages().stream().map(page ->
                            MangaPageEntity.builder().url(page).build()).collect(Collectors.toList()))
                    .build());
            chapterRepository.save(chapter.get());
            return chapter;
        } catch (Exception ex) {
            throw new ChapterNotFoundException("Chapter id " + number + " not found");
        }
    }

}
