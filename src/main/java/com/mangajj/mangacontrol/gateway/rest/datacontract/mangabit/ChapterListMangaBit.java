package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterListMangaBit {

    @JsonProperty("data")
    List<ChapterMangaBit> chapterList = new ArrayList<>();

    @JsonProperty("last_page")
    Integer lastPage;

}
