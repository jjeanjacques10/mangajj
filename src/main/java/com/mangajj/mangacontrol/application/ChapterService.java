package com.mangajj.mangacontrol.application;

import com.mangajj.mangacontrol.adapter.persistence.entity.chapter.ChapterEntity;

import java.util.List;

public interface ChapterService {

    ChapterEntity getChapter(Long mangaId, String number);

    List<ChapterEntity> getMangaChapters(Long mangaId);

}
