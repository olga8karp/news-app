package org.news.newsapiproject.api;

import org.news.newsapiproject.entity.Source;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafTestController {
    @RequestMapping("/test")
    public String showTestPage(Model model) {
        Source source = new Source("1", "CNN");
        model.addAttribute("newsSource", source);
        return "index";
    }
}
