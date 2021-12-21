package com.mangajj.mangacontrol.gateway.rest.datacontract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MangaDataContract {

    private Long id;
    private String title;
    private String synopsis;

}
