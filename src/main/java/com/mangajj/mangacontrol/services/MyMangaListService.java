package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.MangaEntity;

import java.util.List;

public interface MyMangaListService {

    MangaEntity getFromSourceById(Long id);

    List<MangaEntity> getFromSourceByTitle(String title);

}
