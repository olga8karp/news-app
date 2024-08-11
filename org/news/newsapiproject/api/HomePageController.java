package org.news.newsapiproject.api;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.news.newsapiproject.model.ArticleDTO;
import org.news.newsapiproject.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class HomePageController {

    private NewsService newsService;

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("articles", null);
        return "home";
    }

    @RequestMapping("/findArticles")
    public String findArticles(Model model, @RequestParam("query") String query) {
        Set<ArticleDTO> articleDTOS = newsService.getAllArticles(query);
        model.addAttribute("articles", articleDTOS);
        return "home";
    }
}
