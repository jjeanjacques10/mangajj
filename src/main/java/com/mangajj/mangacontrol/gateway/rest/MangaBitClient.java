package com.mangajj.mangacontrol.gateway.rest;

import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBitDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterPagesMangaBitDataContract;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterRequestDataContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "manga-bit", url = "${api.source.mangabit.url}")
public interface MangaBitClient {

    @RequestMapping(method = RequestMethod.GET, value = "/manga/{mangaId}/chapters")
    ChapterMangaBitDataContract getChapter(@PathVariable Long mangaId,
                                           @RequestParam(defaultValue = "0") int page);

    @RequestMapping(method = RequestMethod.GET, value = "/manga/{mangaId}/chapters/{chapterId}/pages")
    ChapterPagesMangaBitDataContract getChapterPages(@PathVariable Long mangaId,
                                                     @PathVariable String chapterId);

    @RequestMapping(method = RequestMethod.POST, value = "/chapters")
    ChapterMangaBitDataContract requestNewChapters(@RequestBody ChapterRequestDataContract chapterRequestDataContract);

}
