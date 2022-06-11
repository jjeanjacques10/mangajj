package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterListMangaBit {

    @JsonProperty("data")
    List<ChapterMangaBit> chapterList;

    @JsonProperty("last_page")
    Integer lastPage;

}
