package org.news.newsapiproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.news.newsapiproject.entity.Source;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.model.ArticlePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@Import({NewsApiClientTest.MockServerConfiguration.class})
class NewsApiClientTest {

    @Autowired
    private NewsApiClient newsApiClient;

    @Autowired
    private MockServerRestTemplateCustomizer mockServerCustomizer;

    @AfterEach
    void tearDown() {
        mockServerCustomizer.getServer().reset();
    }

    @Test
    void getTopHeadlines() {
        mockServerCustomizer.getServer()
                .expect(requestTo(startsWith("https://newsapi.org/v2/top-headlines")))
                .andExpect(queryParam("country", "us"))
                .andExpect(queryParam("page", "0"))
                .andExpect(queryParam("pageSize", "10"))
                .andExpect(queryParam("apiKey", notNullValue()))
                .andRespond(
                        withSuccess()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ClassPathResource("newsapi.org/responses/top-headlines-in-the-us.json"))
                );

        ArticlePageable articlePage = newsApiClient.getTopHeadlines("us", PageRequest.of(0, 10));

        assertEquals(20, articlePage.getPaging().getTotalElements());
    }

    @Test
    void getArticles() {
        mockServerCustomizer.getServer()
                .expect(requestTo(startsWith("https://newsapi.org/v2/everything")))
                .andExpect(queryParam("q", "bitcoin"))
                .andExpect(queryParam("apiKey", notNullValue()))
                .andRespond(
                        withSuccess()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ClassPathResource("newsapi.org/responses/all-articles-about-bitcoin.json"))
                );

        Set<ArticleDTO> articles = newsApiClient.getAllArticles("bitcoin");

        assertEquals(100, articles.size());
    }

    @Test
    void getSources() {
        mockServerCustomizer.getServer()
                .expect(requestTo(startsWith("https://newsapi.org/v2/sources")))
                .andExpect(queryParam("apiKey", notNullValue()))
                .andRespond(
                        withSuccess()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ClassPathResource("newsapi.org/responses/all-sources.json"))
                );

        List<Source> sources = newsApiClient.getSources();

        assertEquals(128, sources.size());
    }

    @TestConfiguration
    static class MockServerConfiguration {

        @Bean
        MockServerRestTemplateCustomizer mockServerRestTemplateCustomizer() {
            return new MockServerRestTemplateCustomizer();
        }

    }
}