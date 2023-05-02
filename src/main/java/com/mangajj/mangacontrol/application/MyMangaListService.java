package com.mangajj.mangacontrol.application;

import com.mangajj.mangacontrol.adapter.persistence.entity.MangaEntity;

import java.util.List;

public interface MyMangaListService {

    MangaEntity getFromSourceById(Long id);

    List<MangaEntity> getFromSourceByTitle(String title);

}
