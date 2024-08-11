package org.news.newsapiproject.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class Languages {
    Map<String, String> languages;

    public Languages() {
        Map<String, String> languages = new HashMap<>();
        languages.put("ar", "Argentina");
        languages.put("de", "Germany");
        languages.put("ee", "Estonia");
        languages.put("es", "Spain");
        languages.put("fr", "France");
        languages.put("he", "Hebrew");
        languages.put("it", "Italy");
        languages.put("nl", "Netherlands");
        languages.put("no", "Norway");
        languages.put("pt", "Portugal");
        languages.put("ru", "Russia");
        languages.put("sv", "El Salvador");
        languages.put("vu", "Vanuatu");
        languages.put("zh", "Simplified Chinese");
        languages.put("dz", "Algeria");
        this.languages = languages;
    }
}