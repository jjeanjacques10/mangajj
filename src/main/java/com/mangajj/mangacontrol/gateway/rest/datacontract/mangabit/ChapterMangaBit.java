package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterMangaBit {

    private String id;
    private String title;
    @JsonProperty("id_manga")
    private int idManga;
    private String url;
    private String number;
    private String releaseDate;
    private StatusChapter status;
    @JsonProperty("created_at")
    private String createdAt;

}
