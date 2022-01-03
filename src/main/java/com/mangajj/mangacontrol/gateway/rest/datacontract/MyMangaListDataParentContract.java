package com.mangajj.mangacontrol.gateway.rest.datacontract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MyMangaListDataParentContract {

    @JsonProperty("request_hash")
    private String request_hash;

    @JsonProperty("request_cached")
    private String request_cached;

    @JsonProperty("request_cache_expiry")
    private String request_cache_expiry;

    private List<MyMangaListDataContract> results;

}
