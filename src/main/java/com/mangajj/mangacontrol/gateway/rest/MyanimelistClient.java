package com.mangajj.mangacontrol.gateway.rest;

import com.mangajj.mangacontrol.gateway.rest.datacontract.WrapperMyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.MyMangaListDataParentContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "manga-list", url = "${api.source.mymangalist.url}")
public interface MyanimelistClient {

    @GetMapping(value = "/manga/{id}")
    WrapperMyMangaListDataContract getMangasMyList(@PathVariable("id") Long id);

    @GetMapping(value = "/manga")
    MyMangaListDataParentContract getMangasByTitle(@RequestParam(value = "q") String title,
                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "limit", defaultValue = "5") int limit,
                                                   @RequestParam(value = "type", defaultValue = "Manga") String type);

}
