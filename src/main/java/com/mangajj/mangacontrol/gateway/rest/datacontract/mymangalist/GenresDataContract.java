package com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenresDataContract {

    @JsonProperty("mal_id")
    private int malId;
    private String type;
    private String name;

}
