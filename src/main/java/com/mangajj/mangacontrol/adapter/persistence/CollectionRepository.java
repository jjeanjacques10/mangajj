package com.mangajj.mangacontrol.adapter.persistence;

import com.mangajj.mangacontrol.adapter.persistence.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

    List<CollectionEntity> findAll();

}
