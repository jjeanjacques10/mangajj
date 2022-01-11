package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.exception.NotFoundManga;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.MyanimelistClient;
import com.mangajj.mangacontrol.services.MyMangaListService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class MyMangaListServiceImpl implements MyMangaListService {

    @Autowired
    private final MyanimelistClient myanimelistClient;

    @Autowired
    private final MangaRepository repository;

    @Override
    public MangaEntity getFromSourceById(Long id) {
        var myManga = myanimelistClient.getMangasMyList(id);

        MangaEntity mangaEntity = saveToDatabase(myManga);
        return mangaEntity;
    }

    @Override
    public List<MangaEntity> getFromSourceByTitle(String title) {
        var mylistResults = myanimelistClient.getMangasByTitle(title, 1, 3);

        if (mylistResults.getResults().isEmpty()) {
            new NotFoundManga("No results for " + title);
        }

        var mangasEntities = mylistResults.getResults()
                .stream().map(manga -> saveToDatabase(manga))
                .filter(mangaEntity -> mangaEntity != null)
                .collect(Collectors.toList());

        return mangasEntities;
    }

    private MangaEntity saveToDatabase(MyMangaListDataContract myManga) {
        MangaEntity mangaEntity = null;
        try {
            Thread.sleep(2000);
            myManga = myanimelistClient.getMangasMyList(myManga.getId());
            mangaEntity = buildMangaEntity(myManga);
            repository.save(mangaEntity);
            log.info("save {} in database", mangaEntity.getTitle());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return mangaEntity;
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
