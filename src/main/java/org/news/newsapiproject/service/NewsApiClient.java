package org.news.newsapiproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Set;

import org.news.newsapiproject.config.NewsApiConfigurationProperties;
import org.news.newsapiproject.entity.Source;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.model.ArticlePageable;
import org.news.newsapiproject.model.Paging;
import org.news.newsapiproject.model.SourcesResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsApiClient {

    private final RestTemplate restTemplate;
    private final NewsApiConfigurationProperties configurationProperties;
    private final NewsParser parser;

    private String apiKey;

    public NewsApiClient(
            RestTemplateBuilder restTemplateBuilder,
            NewsApiConfigurationProperties configurationProperties,
            NewsParser parser) {
        this.restTemplate = restTemplateBuilder.build();
        this.configurationProperties = configurationProperties;
        this.parser = parser;
        this.apiKey = configurationProperties.getApiKey();
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
        return UriComponentsBuilder.fromHttpUrl(configurationProperties.getBaseUrl())
                .path("/top-headlines")
                .queryParam("country", country)
                .queryParam("apiKey", apiKey)
                .queryParam("page", pageNumber)
                .queryParam("pageSize", pageSize)
                .encode()
                .toUriString();
    }

    private String createEverythingRequest(String query, String apiKey){
        return UriComponentsBuilder.fromHttpUrl(configurationProperties.getBaseUrl())
                .path("/everything")
                .queryParam("q", query)
                .queryParam("apiKey", apiKey)
                .encode()
                .toUriString();
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
        String url = createSourcesRequestUrl(apiKey);
        SourcesResponse response = restTemplate.getForObject(url, SourcesResponse.class);
        return response.getSources();
    }

    private String createSourcesRequestUrl(String apiKey) {
        return UriComponentsBuilder.fromHttpUrl(configurationProperties.getBaseUrl())
                .path("/sources")
                .queryParam("apiKey", apiKey)
                .encode()
                .toUriString();
    }

}
