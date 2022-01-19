package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MangaService {

    Page<MangaEntity> getAllMangas(Pageable pageable);

    void createManga(MangaDTO manga);

    void createBatch(List<MangaDTO> mangas);

    MangaEntity getById(Long id);

    List<MangaEntity> getByTitle(String title);

    void deleteById(Long id);
}
