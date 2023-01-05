package com.mangajj.mangacontrol.services.impl.fixtures;

import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;

public class CollectionFixture {
    public static CollectionDTO collectionDTOFixture() {
        return CollectionDTO.builder().mangaId(1L).build();
    }
}
