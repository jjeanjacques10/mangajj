package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MangaDTO {

    private Long id;
    private String title;
    private String status;
    private String synopsis;

}
