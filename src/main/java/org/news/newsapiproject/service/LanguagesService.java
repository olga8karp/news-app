package org.news.newsapiproject.service;

import lombok.Getter;
import org.news.newsapiproject.model.Languages;
import org.springframework.stereotype.Service;

@Getter
@Service
public class LanguagesService {
    Languages languages;

    LanguagesService() {
        languages = new Languages();
    }
}
