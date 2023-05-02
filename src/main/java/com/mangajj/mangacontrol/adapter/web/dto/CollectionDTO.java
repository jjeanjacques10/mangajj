package com.mangajj.mangacontrol.adapter.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollectionDTO {

    private String name;
    private List<MangaDTO> mangas;

}
