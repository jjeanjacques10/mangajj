package com.mangajj.mangacontrol.gateway.rest.datacontract;

import lombok.Data;

import java.util.List;

@Data
public class WrapperMangaScrapperDataContract {

    private String chapter;
    private String manga;
    private List<String> pages;

}
