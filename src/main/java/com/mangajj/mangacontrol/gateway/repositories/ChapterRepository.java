package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entity.chapter.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {

    @Query(value = "SELECT c FROM ChapterEntity c WHERE manga_id = :id")
    List<ChapterEntity> findAllByMangaId(Long id);
}
