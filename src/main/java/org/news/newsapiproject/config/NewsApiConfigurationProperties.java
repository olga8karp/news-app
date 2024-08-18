package org.news.newsapiproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "newsapi")
@Component
@Setter
@Getter
public class NewsApiConfigurationProperties {

    private String apiKey;

    private String baseUrl;

}
