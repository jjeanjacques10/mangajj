package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entity.MangaEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaRepository extends JpaRepository<MangaEntity, Long> {

    List<MangaEntity> findAll();

    @Cacheable(value = "manga")
    List<MangaEntity> findByTitleIgnoreCaseContaining(String title);

}
