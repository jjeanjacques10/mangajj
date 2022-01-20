package com.mangajj.mangacontrol.services;

import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterListMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.PageMangaBit;

import java.util.List;

public interface MangaBitService {

    ChapterListMangaBit getChapters(Long mangaId, String title, int page);

    List<PageMangaBit> getPages(Long mangaId, String chapterId);

}
