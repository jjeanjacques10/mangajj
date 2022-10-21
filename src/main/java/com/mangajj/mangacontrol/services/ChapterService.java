package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.chapter.ChapterEntity;

import java.util.List;

public interface ChapterService {

    ChapterEntity getChapter(Long idChapter);

    List<ChapterEntity> getMangaChapters(Long mangaId);

}
