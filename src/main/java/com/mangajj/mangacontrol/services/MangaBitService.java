package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.PageMangaBit;

import java.util.List;

public interface MangaBitService {

    List<ChapterMangaBit> getChapters(Long mangaId, String title);

    List<PageMangaBit> getPages(Long mangaId, String chapterId);

}
