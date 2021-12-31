package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;
import com.mangajj.mangacontrol.gateway.repositories.CollectionRepository;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.services.CollectionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private final CollectionRepository repository;

    @Autowired
    private final MangaRepository repositoryManga;

    @Override
    public List<CollectionEntity> getAllCollection() {
        return repository.findAll();
    }

    @Override
    public void create(CollectionDTO collectionDTO) {
        var mangas = collectionDTO.getMangas().stream()
                .map(manga -> repositoryManga.findById(manga.getId()).orElse(null)).collect(Collectors.toList());

        var collection = CollectionEntity.builder()
                .name(collectionDTO.getName())
                .mangas(mangas)
                .build();

        repository.save(collection);
    }
}
