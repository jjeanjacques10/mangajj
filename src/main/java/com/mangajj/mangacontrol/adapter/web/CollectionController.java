package com.mangajj.mangacontrol.adapter.web;

import com.mangajj.mangacontrol.adapter.persistence.entity.CollectionEntity;
import com.mangajj.mangacontrol.adapter.web.dto.CollectionDTO;
import com.mangajj.mangacontrol.application.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @GetMapping("/")
    public ResponseEntity<List<CollectionEntity>> getCollections() {
        var collectionList = service.getAllCollection();
        return ResponseEntity.ok(collectionList);
    }

    @PostMapping("/")
    public ResponseEntity<String> createCollection(@RequestBody CollectionDTO collectionDTO) {
        service.create(collectionDTO);
        return ResponseEntity.ok("Collection Created");
    }
}
