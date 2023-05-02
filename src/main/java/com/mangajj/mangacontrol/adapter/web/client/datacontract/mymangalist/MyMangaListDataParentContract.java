package com.mangajj.mangacontrol.adapter.web.client.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MyMangaListDataParentContract {

    @JsonProperty("data")
    private List<MyMangaListDataContract> data;

}
