package com.mangajj.mangacontrol.utils;

import io.micrometer.core.instrument.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetricUtils {
    public static final Tag METRICS_COMMON_TAG = Tag.of("metrics.mangajj.run", "true");

    public static Counter processMangaRequest(MeterRegistry registry, String mangaTitle) {
        return counter(registry, "manga_requests_total", Tag.of("manga_title", mangaTitle));
    }

    private static Counter counter(MeterRegistry registry, String name, Tag... tags) {
        Tags withTags = Tags.of(METRICS_COMMON_TAG).and(tags);
        Counter counter = registry.find(name)
                .tags(withTags)
                .counter();
        if (counter == null) {
            return registry.counter(name, withTags);
        }
        return counter;
    }
}
