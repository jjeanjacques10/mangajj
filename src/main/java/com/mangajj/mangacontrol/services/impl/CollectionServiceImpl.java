package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.entity.UserEntity;
import com.mangajj.mangacontrol.entity.VolumeEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.RequestCollectionDTO;
import com.mangajj.mangacontrol.gateway.repositories.CollectionRepository;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.repositories.UserRepository;
import com.mangajj.mangacontrol.gateway.repositories.VolumeRepository;
import com.mangajj.mangacontrol.services.CollectionService;
import com.mangajj.mangacontrol.shared.exception.NotFoundMangaException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private final CollectionRepository repository;

    @Autowired
    private final MangaRepository mangaRepository;

    @Autowired
    private final VolumeRepository volumeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<CollectionEntity> getAllCollection() {
        return repository.findAll();
    }

    @Override
    public CollectionEntity create(RequestCollectionDTO requestCollectionDTO, UserEntity user) {
        var manga = mangaRepository.findById(requestCollectionDTO.getMangaId()).orElseThrow(() ->
                new NotFoundMangaException("Manga not found with id " + requestCollectionDTO.getMangaId()));
        var owner = userRepository.findById(user.getId()).orElseThrow(() ->
                new NotFoundMangaException("User not found with id " + user.getId()));
        return repository.save(CollectionEntity.builder()
                .manga(manga)
                .owner(owner)
                .build());
    }

    @Override
    public VolumeEntity createVolume(String collectionId, String volumeNumber) {
        var collection = repository.findById(UUID.fromString(collectionId)).orElseThrow(() ->
                new IllegalArgumentException("Collection not found with id " + collectionId));
        return volumeRepository.save(VolumeEntity.builder()
                .collection(collection)
                .number(volumeNumber)
                .build());
    }
}
