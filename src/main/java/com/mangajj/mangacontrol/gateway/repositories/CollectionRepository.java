package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

    List<CollectionEntity> findAll();

}
