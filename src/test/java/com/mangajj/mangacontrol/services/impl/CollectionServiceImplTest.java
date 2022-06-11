package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;
import com.mangajj.mangacontrol.gateway.repositories.CollectionRepository;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.services.CollectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.mangajj.mangacontrol.services.impl.fixtures.CollectionFixture.collectionDTOFixture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollectionServiceImplTest {

    @Mock
    private CollectionRepository repository;

    @Mock
    private MangaRepository repositoryManga;

    @InjectMocks
    private CollectionServiceImpl collectionService;

    @Test
    void shouldGetAllCollections() {
        collectionService.getAllCollection();
        verify(repository).findAll();
    }

    @Test
    void shouldCreateCollection() {
        CollectionDTO collection = collectionDTOFixture();
        when(repositoryManga.findById(collection.getMangas().get(0).getId())).thenReturn(Optional.ofNullable(MangaEntity.builder().build()));

        collectionService.create(collection);

        verify(repository).save(any());
    }

}