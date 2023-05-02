package com.mangajj.mangacontrol.adapter.web.client.datacontract;

import lombok.Data;

import java.util.List;

@Data
public class WrapperMangaScrapperDataContract {

    private String chapter;
    private String manga;
    private List<String> pages;

}
