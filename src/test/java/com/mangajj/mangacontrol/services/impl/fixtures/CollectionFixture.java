package com.mangajj.mangacontrol.services.impl.fixtures;

import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;

import java.util.List;

public class CollectionFixture {
    public static CollectionDTO collectionDTOFixture() {
        MangaDTO manga = MangaDTO.builder().id(1L).title("").build();
        return CollectionDTO.builder().name("Collection 1")
                .mangas(List.of(manga))
                .build();
    }
}
