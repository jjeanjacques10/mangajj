package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;

import java.util.List;

public interface MangaService {

    List<MangaEntity> getAllMangas();

    void createManga(MangaDTO manga);

    void createBatch(List<MangaDTO> mangas);

    void updateLazyLoad(MangaEntity mangaDTO);

    MangaEntity getById(Long id);

    List<MangaEntity> getByTitle(String title);

    void deleteById(Long id);
}
