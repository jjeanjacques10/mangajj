package com.mangajj.mangacontrol.gateway.rest;

import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.MyMangaListDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mymangalist.MyMangaListDataParentContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "manga-list", url = "${api.source.mymangalist.url}")
public interface MyanimelistClient {

    @RequestMapping(method = RequestMethod.GET, value = "/manga/{id}")
    MyMangaListDataContract getMangasMyList(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/search/manga")
    MyMangaListDataParentContract getMangasByTitle(@RequestParam(value = "q") String title,
                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "limit", defaultValue = "5") int limit);

}
