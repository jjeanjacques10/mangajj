package com.mangajj.mangacontrol.gateway.job;

import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.MyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.MyanimelistClient;
import com.mangajj.mangacontrol.services.MangaService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExtractMangasJob {

    @Autowired
    MyanimelistClient client;

    @Autowired
    MangaService service;

    @Autowired
    MangaRepository repository;

    @Async
    //@Scheduled(fixedDelay = 100000000)
    public synchronized void getMangas() {

        var listMangas = repository.findAll();

        listMangas.stream()
                .filter(manga -> manga.getGenres().contains("Hentai")) //TODO: Get by updated date and status
                .forEach(mangaEntity -> {
                    log.info("job delete manga - {} {}", mangaEntity.getId(), mangaEntity.getTitle());
                    repository.delete(mangaEntity);
                   /* try {
                        log.info("job update manga - {} {}", mangaEntity.getId(), mangaEntity.getTitle());
                        var responseMyManga = client.getMangasMyList(mangaEntity.getId());

                        mangaEntity.setVolumes(responseMyManga.getVolumes());
                        mangaEntity.setChapters(responseMyManga.getChapters());
                        mangaEntity.setImageUrl(responseMyManga.getImageUrl());
                        mangaEntity.setPopularity(responseMyManga.getPopularity());
                        mangaEntity.setGenres((ArrayList<String>) responseMyManga.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList()));
                        mangaEntity.setCreatedAt(LocalDateTime.now());
                        mangaEntity.setUpdatedAt(LocalDateTime.now());
                        repository.save(mangaEntity);
                        Thread.sleep(2000);
                    } catch (FeignException e) {
                        log.error("erro manga - {} {} - {}", mangaEntity.getId(), mangaEntity.getTitle(), e.getMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                });
    }

}
