package com.lunatech.bible.controller;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.service.DailyReadingsService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/daily")
public class DailyReadingsController {
    @Autowired
    DailyReadingsService dailyReadingsService;


    @GetMapping("/today") // work on default values
    public List<Kjv> today(@RequestParam(name = "book", defaultValue = "de4e12af7f28f599-01") String book) throws JSONException, SQLException, IOException {
        return dailyReadingsService.readingToday(book);
    }

    @GetMapping("/yesterday") // work on default values
    public List<Kjv> yesterday() throws IOException {
        return dailyReadingsService.readingYesterday();
    }

    @GetMapping("/tomorrow") // work on default values
    public List<Kjv> tomorrow() throws IOException {
        return dailyReadingsService.readingTomorrow();
    }

//    @GetMapping("/count")
//    public Long count() {return randomService.count();}

}
