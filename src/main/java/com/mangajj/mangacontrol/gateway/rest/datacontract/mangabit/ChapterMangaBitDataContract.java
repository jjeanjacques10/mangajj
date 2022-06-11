package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ChapterMangaBitDataContract {

    private int pages;
    private List<ChapterMangaBit> chapters;

}
