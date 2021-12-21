package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.entities.MangaEntity;
import com.mangajj.mangacontrol.gateway.repositories.MangaRepository;
import com.mangajj.mangacontrol.gateway.rest.datacontract.MangaDataContract;
import com.mangajj.mangacontrol.services.MangaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangaRepository repository;

    @Override
    public List<MangaEntity> getAllMangas() {
        return repository.findAll();
    }

    @Override
    public MangaEntity getById(Long id) {
        var mangaFounded = repository.findById(id);
        return mangaFounded.orElseThrow();
    }

    @Override
    public void createManga(MangaDataContract manga) {
        var mangaEntity = MangaEntity.builder()
                .title(manga.getTitle())
                .synopsis(manga.getSynopsis())
                .build();
        repository.save(mangaEntity);
    }

    @Override
    public void createBatch(List<MangaDataContract> mangas) {
        mangas.forEach(this::createManga);
    }

}
