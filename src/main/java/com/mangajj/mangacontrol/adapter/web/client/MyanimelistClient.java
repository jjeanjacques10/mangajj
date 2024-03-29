package com.mangajj.mangacontrol.adapter.web.client;

import com.mangajj.mangacontrol.adapter.web.client.datacontract.WrapperMyMangaListDataContract;
import com.mangajj.mangacontrol.adapter.web.client.datacontract.mymangalist.MyMangaListDataParentContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "manga-list", url = "${api.source.mymangalist.url}")
public interface MyanimelistClient {

    @RequestMapping(method = RequestMethod.GET, value = "/manga/{id}")
    Optional<WrapperMyMangaListDataContract> getMangasMyList(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/manga")
    MyMangaListDataParentContract getMangasByTitle(@RequestParam(value = "q") String title,
                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "limit", defaultValue = "5") int limit,
                                                   @RequestParam(value = "type", defaultValue = "Manga") String type);

}
