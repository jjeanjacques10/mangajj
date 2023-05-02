package com.mangajj.mangacontrol.application;

import com.mangajj.mangacontrol.adapter.persistence.entity.CollectionEntity;
import com.mangajj.mangacontrol.adapter.web.dto.CollectionDTO;

import java.util.List;

public interface CollectionService {

    List<CollectionEntity> getAllCollection();

    void create(CollectionDTO collectionDTO);
}
