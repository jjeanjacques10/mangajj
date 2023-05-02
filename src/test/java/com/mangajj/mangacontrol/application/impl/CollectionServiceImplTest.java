package com.mangajj.mangacontrol.application.impl;

import com.mangajj.mangacontrol.adapter.persistence.entity.MangaEntity;
import com.mangajj.mangacontrol.adapter.web.dto.CollectionDTO;
import com.mangajj.mangacontrol.adapter.persistence.CollectionRepository;
import com.mangajj.mangacontrol.adapter.persistence.MangaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mangajj.mangacontrol.application.impl.fixtures.CollectionFixture.collectionDTOFixture;
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