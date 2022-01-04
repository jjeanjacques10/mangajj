package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.exception.NoMangaFound;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.http.MyanimelistClient;
import com.mangajj.mangacontrol.services.MyMangaListService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class MyMangaListServiceImpl implements MyMangaListService {

    @Autowired
    private final MyanimelistClient myanimelistClient;

    @Override
    public MangaEntity getById(Long id) {
        return null;
    }

    @Override
    public MangaEntity getByTitle(String title) {
        var mylistResults = myanimelistClient.getMangasByTitle(title, 1);
        var myManga = mylistResults.getResults().stream()
                .filter(manga -> manga.getTitle().contains(title))
                .findFirst()
                .orElseThrow(() -> new NoMangaFound("No results for " + title));

        myManga = myanimelistClient.getMangasMyList(myManga.getId());

        return buildMangaEntity(myManga);
    }

    private MangaEntity buildMangaEntity(MyMangaListDataContract myManga) {
        return MangaEntity.builder()
                .id(myManga.getId())
                .status(myManga.getStatus())
                .synopsis(myManga.getSynopsis())
                .title(myManga.getTitle())
                .volumes(myManga.getVolumes())
                .chapters(myManga.getChapters())
                .build();
    }
}
