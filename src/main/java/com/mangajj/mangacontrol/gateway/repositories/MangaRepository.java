package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaRepository extends JpaRepository<MangaEntity, Long> {

    List<MangaEntity> findAll();

    MangaEntity findByTitle(String title);

}
