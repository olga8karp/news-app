package org.news.newsapiproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Set;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.model.SourceDTO;
import org.springframework.stereotype.Component;

@Component
public class NewsParser {

    public Set<ArticleDTO> parseNewsArticles(String jsonResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode articlesNode = root.path("articles"); // array []
        Set<ArticleDTO> articles = new HashSet<>();
        for (JsonNode node : articlesNode) {
            // creating articleDTO object
            ArticleDTO article = new ArticleDTO();
            article.author(node.path("author").asText())
                .title(node.path("title").asText())
                .description(node.path("description").asText())
                .url(node.path("url").asText())
                .urlToImage(node.path("urlToImage").asText())
                .content(node.path("content").asText());
            // getting source info from the node
            String sourceName = node.path("source").path("name").asText();
            String sourceId = node.path("source").path("id").asText();
            // adding source to an article
            article.setSource(new SourceDTO().id(sourceId).name(sourceName));
            // adding article to a set
            articles.add(article);
        }
        return articles;
    }

}


