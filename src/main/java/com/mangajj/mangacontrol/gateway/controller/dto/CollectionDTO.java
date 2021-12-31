package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollectionDTO {

    private String name;
    private List<MangaDTO> mangas;

}
