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
import org.springframework.transaction.annotation.Transactional;

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
        if (myManga == null) throw new NotFoundManga("Manga not found id " + id);

        return saveToDatabase(myManga.getData());
    }

    @Override
    public List<MangaEntity> getFromSourceByTitle(String title) {
        var mylistResults = myanimelistClient.getMangasByTitle(title, 1, 4, "Manga");

        if (mylistResults.getData().isEmpty()) throw new NotFoundManga("No results for " + title);

        return mylistResults.getData()
                .stream()
                .map(this::saveToDatabase)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    private MangaEntity saveToDatabase(MyMangaListDataContract myManga) {
        try {
            Thread.sleep(1000);
            myManga = myanimelistClient.getMangasMyList(myManga.getId()).getData();
            if (!isValidGenre(myManga.getGenres())) return null;
            var mangaEntity = buildMangaEntity(myManga);
            repository.save(mangaEntity);
            log.info("save {} in database", mangaEntity.getTitle());
            return mangaEntity;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
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
                .imageUrl(myManga.getImages().getJpg().getLargeImageUrl())
                .popularity(myManga.getPopularity())
                .genres((ArrayList<String>) myManga.getGenres().stream().map(GenresDataContract::getName).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
