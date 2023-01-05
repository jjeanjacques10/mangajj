package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.entity.UserEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.CollectionDTO;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.controller.dto.ResponseCollectionDTO;
import com.mangajj.mangacontrol.gateway.controller.dto.ResponseDTO;
import com.mangajj.mangacontrol.services.CollectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @Autowired
    private ModelMapper mapper;

    // TODO: create a object DTO to return collection
    @GetMapping
    public ResponseEntity<List<CollectionEntity>> getCollections() {
        var collectionList = service.getAllCollection();
        return ResponseEntity.ok(collectionList);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCollection(@RequestBody CollectionDTO collectionDTO, Authentication authentication) {
        UserEntity owner = (UserEntity) authentication.getPrincipal();

        var collection = service.create(collectionDTO, owner);

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(ResponseCollectionDTO.builder()
                        .id(collection.getId())
                        .manga(mapper.map(collection.getManga(), MangaDTO.class))
                        .message("collection created").build()).build());
    }

    // TODO: Fix create list result
    @PostMapping("/{collectionId}/volume/{volumeNumber}")
    public ResponseEntity<ResponseDTO> createVolume(@PathVariable String collectionId, @PathVariable String volumeNumber) {
        var volume = service.createVolume(collectionId, volumeNumber);
        return ResponseEntity.ok(ResponseDTO.builder()
                .data(volume)
                .build());
    }
}
