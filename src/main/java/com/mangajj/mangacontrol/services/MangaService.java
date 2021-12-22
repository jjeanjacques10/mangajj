package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entities.MangaEntity;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MangaDataContract;

import java.util.List;

public interface MangaService {

    List<MangaEntity> getAllMangas();

    void createManga(MangaDataContract manga);

    void createBatch(List<MangaDataContract> mangas);

    MangaEntity getById(Long id);

    MangaEntity getByTitle(String title);
}
