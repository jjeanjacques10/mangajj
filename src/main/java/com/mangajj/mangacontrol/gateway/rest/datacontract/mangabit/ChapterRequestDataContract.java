package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChapterRequestDataContract {

    @JsonProperty("id")
    private Long mangaId;
    private String title;

}
