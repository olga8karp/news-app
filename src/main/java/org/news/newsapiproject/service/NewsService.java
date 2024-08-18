package org.news.newsapiproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.news.newsapiproject.entity.Source;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.model.ArticlePageable;
import org.news.newsapiproject.model.Paging;
import org.news.newsapiproject.model.SourcesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    private final RestTemplate restTemplate;
    private final NewsParser parser;

    @Value("${newsapi.key}")
    private String apiKey;

    @Value("${requests.topHeadlines}")
    private String topHeadlinesRequest;

    @Value("${requests.everything}")
    private String everythingRequest;

    public NewsService(RestTemplate restTemplate, NewsParser parser) {
        this.restTemplate = restTemplate;
        this.parser = parser;
    }

    public ArticlePageable getTopHeadlines(String country, Pageable pageable) {
        String url = createRequestUrl(country, apiKey, pageable.getPageNumber(), pageable.getPageSize());
        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            Set<ArticleDTO> articles = parser.parseNewsArticles(jsonResponse);
            Paging paging = new Paging((int) pageable.getOffset(), pageable.getPageSize());
            paging.setTotalElements(articles.size());
            paging.setTotalPages((int) Math.ceil(articles.size() / (double) pageable.getPageSize()));
            paging.setCurrentPage(pageable.getPageNumber());

            return new ArticlePageable()
                .data(articles)
                .paging(paging);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createRequestUrl(String country, String apiKey, int pageNumber, int pageSize) {
        return topHeadlinesRequest
            + "?country=" + country
            + "&apiKey=" + apiKey
            + "&page=" + pageNumber
            + "&pageSize=" + pageSize;
    }

    private String createEverythingRequest(String query, String apiKey){
        return everythingRequest
            + "?q=" + query
            + "&apiKey=" + apiKey;
    }

    public Set<ArticleDTO> getAllArticles(String query) {
        String url = createEverythingRequest(query, apiKey);
        String jsonResponse = restTemplate.getForObject(url, String.class);
        try {
            return parser.parseNewsArticles(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Source> getSources() {
        Map<String, String> vars = new HashMap<>();
        vars.put("apiKey", apiKey);
        SourcesResponse response = restTemplate.getForObject("https://newsapi.org/v2/top-headlines/sources?apiKey={apiKey}", SourcesResponse.class, vars);
        return response.getSources();
    }

}
