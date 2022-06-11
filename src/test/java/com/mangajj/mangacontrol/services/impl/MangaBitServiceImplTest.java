package com.mangajj.mangacontrol.services.impl;

import com.mangajj.mangacontrol.gateway.rest.MangaBitClient;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterListMangaBit;
import com.mangajj.mangacontrol.gateway.rest.datacontract.mangabit.ChapterRequestDataContract;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.mangajj.mangacontrol.services.impl.fixtures.ChapterMangaBitDataContractFixture.getChapterMangaBitDataContract;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MangaBitServiceImplTest {

    @Mock
    private MangaBitClient mangaBitClient;

    @InjectMocks
    private MangaBitServiceImpl mangaBitService;

    @Test
    public void shouldGetChaptersWithMangaIdAndTitle() {
        Long mangaId = 1L;
        var title = "";
        int page = 1;
        ChapterListMangaBit chapterListExpected = new ChapterListMangaBit(getChapterMangaBitDataContract().getChapters(), 1);

        when(mangaBitClient.getChapter(mangaId, page)).thenReturn(getChapterMangaBitDataContract());
        ChapterListMangaBit chaptersList = mangaBitService.getChapters(mangaId, title, page);

        assertEquals(chapterListExpected, chaptersList);
    }

    @Test
    public void shouldNotGetChaptersThrowAPIError() {
        Long mangaId = 1L;
        var title = "";
        int page = 1;
        ChapterListMangaBit chapterListExpected = new ChapterListMangaBit();

        Map<String, Collection<String>> headers = Map.of();
        Request request = Request.create(Request.HttpMethod.GET, "headers", headers, Request.Body.create(""), null);
        Response response = Response.builder().status(500).reason("error in request").headers(headers).request(request).build();
        when(mangaBitClient.getChapter(mangaId, page)).thenThrow(FeignException.errorStatus("GET", response));

        ChapterListMangaBit chaptersList = mangaBitService.getChapters(mangaId, title, page);

        var chapterRequested = ChapterRequestDataContract.builder().mangaId(mangaId).title(title).build();

        verify(mangaBitClient).requestNewChapters(chapterRequested);
        assertEquals(chapterListExpected, chaptersList);
    }

    @Test
    public void shouldNotGetChaptersThrowAPIException() {
        Long mangaId = 1L;
        var title = "";
        int page = 1;
        ChapterListMangaBit chapterListExpected = new ChapterListMangaBit();

        when(mangaBitClient.getChapter(mangaId, page)).thenThrow(new RuntimeException("Cannot process"));
        ChapterListMangaBit chaptersList = mangaBitService.getChapters(mangaId, title, page);

        assertEquals(chapterListExpected, chaptersList);
    }

}