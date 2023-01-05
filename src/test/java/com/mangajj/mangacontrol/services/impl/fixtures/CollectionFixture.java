package com.mangajj.mangacontrol.services.impl.fixtures;

import com.mangajj.mangacontrol.gateway.controller.dto.RequestCollectionDTO;

public class CollectionFixture {
    public static RequestCollectionDTO collectionDTOFixture() {
        return RequestCollectionDTO.builder().mangaId(1L).build();
    }
}
