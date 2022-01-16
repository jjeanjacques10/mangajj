package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import lombok.Data;

import java.util.List;

@Data
public class ChapterMangaBitDataContract {

    private int pages;
    private List<ChapterMangaBit> chapters;

}
