package com.mangajj.mangacontrol.gateway.rest.http;

import com.mangajj.mangacontrol.gateway.rest.datacontract.MyMangaListDataContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "manga-list", url = "${api.source.mymangalist.url}")
public interface MyanimelistClient {

    @RequestMapping(method = RequestMethod.GET, value = "/manga/{id}")
    MyMangaListDataContract getMangasMyList(@PathVariable int id);

}
