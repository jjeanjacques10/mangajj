package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.chapter.ChapterEntity;
import com.mangajj.mangacontrol.gateway.repositories.ChapterRepository;
import com.mangajj.mangacontrol.services.ChapterService;
import com.mangajj.mangacontrol.shared.exception.ChapterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

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
    public ChapterEntity getChapter(Long idChapter) {
        return chapterRepository.findById(idChapter)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter id " + idChapter + "not found"));
    }

}
