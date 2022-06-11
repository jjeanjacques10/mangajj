package com.mangajj.mangacontrol.services.impl.fixtures;

import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBitDataContract;

import java.util.List;

public class ChapterMangaBitDataContractFixture {

    public static ChapterMangaBitDataContract getChapterMangaBitDataContract() {
        return ChapterMangaBitDataContract.builder()
                .chapters(List.of(ChapterMangaBit.builder()
                        .idManga(1)
                        .build()))
                .pages(1)
                .build();
    }

}
