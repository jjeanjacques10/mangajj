package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.shared.exception.NotFoundManga;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.rest.MyanimelistClient;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.GenresDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.MyMangaListDataContract;
import com.mangajj.mangacontrol.services.MyMangaListService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        return saveToDatabase(myManga);
    }

    @Override
    public List<MangaEntity> getFromSourceByTitle(String title) {
        var mylistResults = myanimelistClient.getMangasByTitle(title, 1, 4, "Manga");

        if (mylistResults.getResults().isEmpty()) {
            throw new NotFoundManga("No results for " + title);
        }

        return mylistResults.getResults()
                .stream()
                .map(this::saveToDatabase)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private MangaEntity saveToDatabase(MyMangaListDataContract myManga) {
        MangaEntity mangaEntity = null;
        try {
            Thread.sleep(2000);
            myManga = myanimelistClient.getMangasMyList(myManga.getId());
            if (isValidGenre(myManga.getGenres())) {
                mangaEntity = buildMangaEntity(myManga);
                repository.save(mangaEntity);
                log.info("save {} in database", mangaEntity.getTitle());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return mangaEntity;
    }

    private boolean isValidGenre(List<GenresDataContract> genres) {
        return genres.stream().anyMatch(g -> g.getMalId() != 12);
    }

    private MangaEntity buildMangaEntity(MyMangaListDataContract myManga) {
        return MangaEntity.builder()
                .id(myManga.getId())
                .status(myManga.getStatus())
                .synopsis(myManga.getSynopsis())
                .title(myManga.getTitle())
                .volumes(myManga.getVolumes())
                .chapters(myManga.getChapters())
                .imageUrl(myManga.getImageUrl())
                .popularity(myManga.getPopularity())
                .genres((ArrayList<String>) myManga.getGenres().stream().map(GenresDataContract::getName).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
