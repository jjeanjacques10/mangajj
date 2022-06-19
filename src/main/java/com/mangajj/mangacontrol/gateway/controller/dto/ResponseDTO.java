package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDTO<T> {
    private T data;
}