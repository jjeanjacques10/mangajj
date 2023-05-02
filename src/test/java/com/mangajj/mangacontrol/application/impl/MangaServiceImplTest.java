package com.mangajj.mangacontrol.application.impl;

import com.mangajj.mangacontrol.adapter.persistence.entity.MangaEntity;
import com.mangajj.mangacontrol.adapter.persistence.MangaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MangaServiceImplTest {

    @InjectMocks
    MangaServiceImpl mangaService;

    @Mock
    MangaRepository repository;

    @Mock
    MyMangaListServiceImpl myMangaListService;

    @Test
    void shouldGetByIdFromDatabase() {
        Long idManga = 11L;
        String nameManga = "Naruto";
        MangaEntity mangaEntityReturn = MangaEntity.builder().id(idManga).title(nameManga).build();

        when(repository.findById(idManga))
                .thenReturn(Optional.ofNullable(mangaEntityReturn));

        MangaEntity mangaEntity = mangaService.getById(idManga);

        assertEquals(idManga, mangaEntity.getId());
        assertEquals("Naruto", mangaEntity.getTitle());
    }

    @Test
    void shouldGetByIdFromAPI() {
        Long idManga = 11L;
        String nameManga = "Naruto";
        MangaEntity mangaEntityReturn = MangaEntity.builder().id(idManga).title(nameManga).build();

        when(repository.findById(idManga))
                .thenReturn(Optional.empty());

        when(myMangaListService.getFromSourceById(idManga))
                .thenReturn(mangaEntityReturn);

        MangaEntity mangaEntity = mangaService.getById(idManga);

        assertEquals(idManga, mangaEntity.getId());
        assertEquals("Naruto", mangaEntity.getTitle());
    }

}