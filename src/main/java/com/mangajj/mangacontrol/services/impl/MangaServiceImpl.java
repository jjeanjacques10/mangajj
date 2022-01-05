package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entity.MangaEntity;
import com.mangajj.mangacontrol.gateway.controller.dto.MangaDTO;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.services.MangaService;
import com.mangajj.mangacontrol.services.MyMangaListService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class MangaServiceImpl implements MangaService {

    private final MangaRepository repository;
    private final MyMangaListService myMangaListService;

    @Override
    public List<MangaEntity> getAllMangas() {
        log.info("Getting all mangas");
        return repository.findAll();
    }

    @Override
    public MangaEntity getById(Long id) {
        log.info("Get by Id [{}]", id);
        var mangaFounded = repository.findById(id).orElseThrow();

        if (mangaFounded == null) {
            mangaFounded = myMangaListService.getById(id);
            repository.save(mangaFounded);
        }

        return mangaFounded;
    }

    @Override
    public void updateLazyLoad(MangaEntity mangaEntity) {
        mangaEntity = myMangaListService.getByTitle(mangaEntity.getTitle());
        repository.save(mangaEntity);
    }

    @Override
    public List<MangaEntity> getByTitle(String title) {
        log.info("Get by Title [{}]", title);
        var mangaEntities = repository.findByTitleIgnoreCaseContaining(title);

        if (mangaEntities.isEmpty()) {
            var mangaEntity = myMangaListService.getByTitle(title);
            mangaEntities.add(mangaEntity);
            repository.save(mangaEntity);
        }

        return mangaEntities;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void createManga(MangaDTO manga) {
        var mangaEntity = MangaEntity.builder()
                .id(manga.getId())
                .title(manga.getTitle())
                .status(manga.getStatus())
                .synopsis(manga.getSynopsis())
                .build();
        repository.save(mangaEntity);
    }

    @Override
    public void createBatch(List<MangaDTO> mangas) {
        mangas.forEach(this::createManga);
    }

}
