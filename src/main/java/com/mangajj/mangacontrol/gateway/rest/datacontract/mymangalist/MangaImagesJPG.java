package com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MangaImagesJPG {
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("small_image_url")
    private String smallImageUrl;

    @JsonProperty("large_image_url")
    private String largeImageUrl;
}
