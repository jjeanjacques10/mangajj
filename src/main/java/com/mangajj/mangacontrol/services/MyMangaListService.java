package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.MangaEntity;

public interface MyMangaListService {

    MangaEntity getById(Long id);

    MangaEntity getByTitle(String title);

}
