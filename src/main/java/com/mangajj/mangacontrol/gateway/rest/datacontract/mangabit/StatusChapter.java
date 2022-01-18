package com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusChapter {

    PROCESSING("Processing"),
    DONE("Done"),
    NOT_FOUND("Not Found");

    public final String label;

    StatusChapter(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return this.label;
    }
}
