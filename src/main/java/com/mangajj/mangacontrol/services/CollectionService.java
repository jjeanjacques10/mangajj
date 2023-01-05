package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.entity.UserEntity;
import com.mangajj.mangacontrol.entity.VolumeEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.RequestCollectionDTO;

import java.util.List;

public interface CollectionService {

    List<CollectionEntity> getAllCollection();

    CollectionEntity create(RequestCollectionDTO requestCollectionDTO, UserEntity user);

    VolumeEntity createVolume(String collectionId, String volumeNumber);
}
