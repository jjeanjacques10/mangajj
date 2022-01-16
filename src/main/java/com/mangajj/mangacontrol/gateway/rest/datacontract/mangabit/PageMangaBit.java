package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageMangaBit {

    private String id;
    private String chapterId;
    @JsonProperty("page_number")
    private int pageNumber;
    private String imageUrl;

}
