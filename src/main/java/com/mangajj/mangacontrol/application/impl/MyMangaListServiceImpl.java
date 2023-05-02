package com.mangajj.mangacontrol.application.impl;

import com.mangajj.mangacontrol.adapter.persistence.entity.MangaEntity;
import com.mangajj.mangacontrol.adapter.persistence.MangaRepository;
import com.mangajj.mangacontrol.adapter.web.client.MyanimelistClient;
import com.mangajj.mangacontrol.adapter.web.client.datacontract.mymangalist.GenresDataContract;
import com.mangajj.mangacontrol.adapter.web.client.datacontract.mymangalist.MyMangaListDataContract;
import com.mangajj.mangacontrol.application.MyMangaListService;
import com.mangajj.mangacontrol.application.exception.NotFoundMangaException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        if (myManga == null) throw new NotFoundMangaException("Manga not found id " + id);
        return saveToDatabase(myManga.getData()).orElseThrow(() -> new NotFoundMangaException("Manga not found id " + id));
    }

    @Override
    public List<MangaEntity> getFromSourceByTitle(String title) {
        var mylistResults = myanimelistClient.getMangasByTitle(title, 1, 4, "Manga");

        if (mylistResults.getData().isEmpty()) throw new NotFoundMangaException("No results for " + title);

        List<MangaEntity> mangaEntities = mylistResults.getData()
                .stream()
                .map(m -> getMyMangaListItem(m, true))
                .filter(Objects::nonNull)
                .map(this::buildMangaEntity)
                .collect(Collectors.toList());
        mangaEntities.forEach(m -> log.info("save {} in database", m.getTitle()));
        return repository.saveAll(mangaEntities);
    }

    @Transactional
    private Optional<MangaEntity> saveToDatabase(MyMangaListDataContract myManga) {
        try {
            var mangaEntity = buildMangaEntity(getMyMangaListItem(myManga, false));
            repository.save(mangaEntity);
            return Optional.of(mangaEntity);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }

    private MyMangaListDataContract getMyMangaListItem(MyMangaListDataContract myManga, boolean withSleep) {
        MyMangaListDataContract manga = null;
        try {
            if (withSleep) Thread.sleep(1000);
            manga = myanimelistClient.getMangasMyList(myManga.getId()).getData();
            if (!isValidGenre(manga.getGenres())) return null;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return manga;
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
                .chaptersNumber(myManga.getChapters())
                .imageUrl(myManga.getImages().getJpg().getLargeImageUrl())
                .popularity(myManga.getPopularity())
                .genres((ArrayList<String>) myManga.getGenres().stream().map(GenresDataContract::getName).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
