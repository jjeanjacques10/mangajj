package com.mangajj.mangacontrol.gateway.rest.datacontract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MyMangaListDataContract {

    @JsonProperty("mal_id")
    private Long id;
    private String url;
    private String title;
    private String status;
    private String synopsis;
    private String background;

}
