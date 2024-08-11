package org.news.newsapiproject.service;

import lombok.Getter;
import org.news.newsapiproject.model.Countries;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CountriesService {
    Countries countries;

    CountriesService() {
        countries = new Countries();
    }
}