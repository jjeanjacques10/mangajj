package com.mangajj.mangacontrol.adapter.web.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDetailsDTO {
    private String title;
    private Integer status;
    private String details;
    private String timestamp;
    private String developerMethod;
}
