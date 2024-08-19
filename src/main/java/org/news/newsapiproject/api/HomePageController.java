package org.news.newsapiproject.api;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.news.newsapiproject.entity.Source;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.model.Countries;
import org.news.newsapiproject.model.Languages;
import org.news.newsapiproject.service.NewsApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class HomePageController {
    private NewsApiClient newsApiClient;

    @RequestMapping("/")
    public String getHomePage(Model model) {
        setFilterOptions(model);
        model.addAttribute("articles", null);
        return "home";
    }

    @RequestMapping("/findArticles")
    public String findArticles(Model model, @RequestParam("query") String query) {
        setFilterOptions(model);
        Set<ArticleDTO> articleDTOS = newsApiClient.getAllArticles(query);
        model.addAttribute("articles", articleDTOS);
        return "home";
    }

    private void setFilterOptions(Model model) {
        List<Source> sources = newsApiClient.getSources();
        model.addAttribute("sources", sources);
        Languages languages = new Languages();
        model.addAttribute("languages", languages.getLanguages());
        Countries countries = new Countries();
        model.addAttribute("countries", countries.getCountries());
    }
}
