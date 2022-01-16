package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChapterPagesMangaBitDataContract {

    @JsonProperty("total_pages")
    private int totalPages;
    private StatusChapter status;
    private List<PageMangaBit> data;

}
