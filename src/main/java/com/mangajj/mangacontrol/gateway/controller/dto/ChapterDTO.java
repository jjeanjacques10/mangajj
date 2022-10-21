package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {

    private Long id;
    private String title;
    private String number;
    private LocalDateTime releaseDate;
    private List<MangaPageDto> pages;

}
