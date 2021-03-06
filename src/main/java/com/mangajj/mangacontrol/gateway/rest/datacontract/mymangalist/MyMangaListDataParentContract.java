package com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MyMangaListDataParentContract {

    @JsonProperty("request_hash")
    private String requestHash;

    @JsonProperty("request_cached")
    private String requestCached;

    @JsonProperty("request_cache_expiry")
    private String requestCacheExpiry;

    private List<MyMangaListDataContract> results;

}
