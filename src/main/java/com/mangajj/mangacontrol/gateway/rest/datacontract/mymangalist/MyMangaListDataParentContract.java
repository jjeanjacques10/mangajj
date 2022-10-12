package com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MyMangaListDataParentContract {

    @JsonProperty("data")
    private List<MyMangaListDataContract> data;

}
