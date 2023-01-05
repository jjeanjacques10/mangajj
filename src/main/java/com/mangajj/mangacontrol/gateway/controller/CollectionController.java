package com.mangajj.mangacontrol.gateway.controller;

import com.mangajj.mangacontrol.entity.CollectionEntity;
import com.mangajj.mangacontrol.entity.UserEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.*;
import com.mangajj.mangacontrol.services.CollectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getCollections() {
        var collectionList = service.getAllCollection();
        List<CollectionDTO> collectionDTO = collectionList.stream().map(c -> mapper.map(c, CollectionDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(collectionDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCollection(@RequestBody RequestCollectionDTO requestCollectionDTO, Authentication authentication) {
        UserEntity owner = (UserEntity) authentication.getPrincipal();

        var collection = service.create(requestCollectionDTO, owner);

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
