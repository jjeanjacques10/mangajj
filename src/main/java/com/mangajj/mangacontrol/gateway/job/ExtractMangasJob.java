package com.mangajj.mangacontrol.gateway.job;

import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.http.MyanimelistClient;
import com.mangajj.mangacontrol.services.MangaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtractMangasJob {

    @Autowired
    MyanimelistClient client;

    @Autowired
    MangaService service;

    //@Scheduled(fixedDelay = 100000000)
    public void getMangas() {
        for (int i = 1; i <= 4500; i++) {
            try {
                var responseMyManga = client.getMangasMyList(i);

                var mangaEntity = service.getByTitle(responseMyManga.getTitle());
                if (mangaEntity == null) insertMangaDatabase(responseMyManga);
            } catch (FeignException e) {
                System.out.println("Error - " + e.getMessage());
            }
        }
    }

    private void insertMangaDatabase(MyMangaListDataContract myManga) {
        System.out.println("Creating in Database manga - " + myManga.getTitle());
        MangaDTO manga = MangaDTO.builder()
                .id(myManga.getId())
                .status(myManga.getStatus())
                .title(myManga.getTitle())
                .synopsis(myManga.getSynopsis())
                .build();
        service.createManga(manga);
    }
}
