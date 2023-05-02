package com.mangajj.mangacontrol.adapter.web.client.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MyMangaListDataContract {

    @JsonProperty("mal_id")
    private Long id;
    private String url;
    private String title;
    private String status;
    private int volumes;
    private int chapters;
    private int popularity = 0;
    private String synopsis;
    private String type;
    private String background;
    private MangaImages images;
    private List<GenresDataContract> genres;


}
