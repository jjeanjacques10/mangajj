package com.mangajj.mangacontrol.gateway.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterListMangaBit;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MangaDTO {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String status;
    private int volumes;
    private int chapters;
    private Integer popularity;
    private String imageUrl;
    private String synopsis;
    @JsonProperty("chapters_list")
    private ChapterListMangaBit chaptersList;

}
