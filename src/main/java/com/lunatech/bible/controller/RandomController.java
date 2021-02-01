package com.lunatech.bible.controller;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/random")
public class RandomController {
    @Autowired
    RandomService randomService;

    @GetMapping("/search") // work on default values
    public ResponseEntity<String> search(@RequestParam(name = "book", defaultValue = "de4e12af7f28f599-01") String book,
                                         @RequestParam(name="chapter", defaultValue = "") String chapter,
                                         @RequestParam(name="verse", defaultValue = "10") String verse) {
        return randomService.search(book, chapter, verse);
    }

    @RequestMapping("/verse")
    public List<Kjv> random(@RequestParam(name="length", defaultValue = "10") int length) {
//        model.addAttribute("appName", appName);
        return randomService.searchRandom(length);
    }

//    @GetMapping("/count")
//    public Long count() {return randomService.count();}

}
