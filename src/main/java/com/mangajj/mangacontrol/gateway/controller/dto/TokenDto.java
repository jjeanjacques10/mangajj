package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private final String token;
    private final String bearer;
}