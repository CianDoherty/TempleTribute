package com.lunatech.bible.controller;

import com.lunatech.bible.model.KeyEnglish;
import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.repository.KeyEnglishTranslator;
import com.lunatech.bible.service.OracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private DailyReadingsController dailyReadingsController;
    @Autowired
    private RandomController randomController;
    @Autowired
    private KeyEnglishTranslator keyEnglishTranslator;
    @Autowired
    private OracleService oracleController;

    public WebController(DailyReadingsController dailyReadingsController) {
        this.dailyReadingsController = dailyReadingsController;
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    @RequestMapping("/daily")
    public String dailyPage(Model model, @RequestParam(name = "day", defaultValue = "today") String day) {

        model.addAttribute("appName", appName);
        List<Kjv> verses = null;
        if (day.equals("yesterday")) verses = dailyReadingsController.yesterday();
        else if (day.equals("tomorrow")) verses = dailyReadingsController.tomorrow();
        else verses = dailyReadingsController.today();
        List<String> names = retrieveBookNames(verses);
        model.addAttribute("verses", verses);
        model.addAttribute("names", names);
        return "daily";
    }

    public List<String> retrieveBookNames(List<Kjv> verses) {
        List<KeyEnglish> allKeys = keyEnglishTranslator.findAll();
        List<String> correspondingBookNames = new ArrayList<>();
        for (int i = 0;i<verses.size();i++) {
            int finalI = i;
            List<KeyEnglish> result = allKeys.stream()
                    .filter(item -> item.getB().equals(verses.get(finalI).getB()))
                    .collect(Collectors.toList());
            correspondingBookNames.add(result.get(0).getN());
            // compare each verse, find the corresponding name to key and create a new list
        }
        return correspondingBookNames;
    }


    @RequestMapping("/random")
    public String randomPage(Model model, @RequestParam(name = "verseCount", defaultValue = "10") String verseCount) {
        model.addAttribute("appName", appName);
        boolean isInteger = isInt(verseCount);
        if (!isInteger) verseCount = "10";
        List<Kjv> verses = randomController.random(Integer.valueOf(verseCount));

        List<String> names = retrieveBookNames(verses);
        model.addAttribute("verses", verses);
        model.addAttribute("names", names);
        return "random";
    }
    static boolean isInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true; }

        catch(NumberFormatException er)
        { return false; }
    }

    public String oracleString = "";
    @RequestMapping("/oracle")
    public String oraclePage(Model model, @RequestParam(name = "reset", defaultValue = "false") boolean reset) {
        model.addAttribute("appName", appName);

        String word = oracleController.oracle();

        oracleString = oracleString + " " + word;
        if(reset)oracleString = "";
        model.addAttribute("word", oracleString);
        return "oracle";
    }
}
