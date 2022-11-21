package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entity.MangaEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity, Long> {

    List<MangaEntity> findAll();

    @Cacheable(value = "manga")
    List<MangaEntity> findByTitleIgnoreCaseContaining(String title);

}
