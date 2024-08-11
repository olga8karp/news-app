package org.news.newsapiproject.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class Countries {
    Map<String, String> countries;

    public Countries() {
        Map<String, String> countries = new HashMap<>();
        countries.put("ae", "United Arab Emirates");
        countries.put("ar", "Argentina");
        countries.put("at", "Austria");
        countries.put("au", "Australia");
        countries.put("be", "Belgium");
        countries.put("bg", "Bulgaria");
        countries.put("br", "Brazil");
        countries.put("ca", "Canada");
        countries.put("ch", "Switzerland");
        countries.put("cn", "China");
        countries.put("co", "Colombia");
        countries.put("cu", "Cuba");
        countries.put("cz", "Czech Republic");
        countries.put("de", "Germany");
        countries.put("eg", "Egypt");
        countries.put("fr", "France");
        countries.put("gb", "United Kingdom");
        countries.put("gr", "Greece");
        countries.put("hk", "Hong Kong");
        countries.put("hu", "Hungary");
        countries.put("id", "Indonesia");
        countries.put("ie", "Ireland");
        countries.put("il", "Israel");
        countries.put("in", "India");
        countries.put("it", "Italy");
        countries.put("jp", "Japan");
        countries.put("kr", "South Korea");
        countries.put("lt", "Lithuania");
        countries.put("lv", "Latvia");
        countries.put("ma", "Morocco");
        countries.put("mx", "Mexico");
        countries.put("my", "Malaysia");
        countries.put("ng", "Nigeria");
        countries.put("nl", "Netherlands");
        countries.put("no", "Norway");
        countries.put("nz", "New Zealand");
        countries.put("ph", "Philippines");
        countries.put("pl", "Poland");
        countries.put("pt", "Portugal");
        countries.put("ro", "Romania");
        countries.put("rs", "Serbia");
        countries.put("ru", "Russia");
        countries.put("sa", "Saudi Arabia");
        countries.put("se", "Sweden");
        countries.put("sg", "Singapore");
        countries.put("si", "Slovenia");
        countries.put("sk", "Slovakia");
        countries.put("th", "Thailand");
        countries.put("tr", "Turkey");
        countries.put("tw", "Taiwan");
        countries.put("ua", "Ukraine");
        countries.put("us", "United States");
        countries.put("ve", "Venezuela");
        countries.put("za", "South Africa");
        this.countries = countries;
    }
}