package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;

import java.util.List;

public interface CollectionService {

    List<CollectionEntity> getAllCollection();

    void create(CollectionDTO collectionDTO);
}
