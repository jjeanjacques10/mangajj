package com.mangajj.mangacontrol.gateway.rest;

import com.mangajj.mangacontrol.gateway.rest.datacontract.WrapperMangaScrapperDataContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "manga-scrapper", url = "${api.source.mangascrapper.url}")
public interface MangaScrapperClient {

    @RequestMapping(method = RequestMethod.GET, value = "/chapter")
    WrapperMangaScrapperDataContract getMangasMyList(@RequestParam(value = "source") String source,
                                                     @RequestParam(value = "manga") String manga,
                                                     @RequestParam(value = "number") String number);
}
