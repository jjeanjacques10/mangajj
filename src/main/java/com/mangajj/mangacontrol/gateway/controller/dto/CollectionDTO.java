package com.mangajj.mangacontrol.gateway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDTO {

    private UUID id;

    private MangaDTO manga;

    private UserDTO owner;

}
