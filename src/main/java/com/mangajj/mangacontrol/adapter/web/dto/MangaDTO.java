package com.mangajj.mangacontrol.adapter.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MangaDTO {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String status;
    private int volumes;
    private int chaptersNumber;
    private Integer popularity;
    private String imageUrl;
    private String synopsis;
    private List<ChapterDTO> chapters;

}
