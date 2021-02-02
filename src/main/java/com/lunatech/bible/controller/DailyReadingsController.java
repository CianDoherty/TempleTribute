package com.lunatech.bible.controller;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.service.DailyReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/daily")
public class DailyReadingsController {
    @Autowired
    DailyReadingsService dailyReadingsService;


    @GetMapping("/today")
    public List<Kjv> today() {
        return dailyReadingsService.readingToday();
    }

    @GetMapping("/yesterday")
    public List<Kjv> yesterday() {
        return dailyReadingsService.readingYesterday();
    }

    @GetMapping("/tomorrow")
    public List<Kjv> tomorrow()  {
        return dailyReadingsService.readingTomorrow();
    }

}
