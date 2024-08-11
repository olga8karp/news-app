package org.news.newsapiproject.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.news.newsapiproject.model.ArticlePageable;
import org.news.newsapiproject.service.NewsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NewsController implements NewsApi{

    private final NewsService newsService;

    @Override
    public ResponseEntity<ArticlePageable> getTopHeadlines(String country, Integer page, Integer size) {
        log.info("Request received: " + country);
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(newsService.getTopHeadlines(country, paging));
    }
}
