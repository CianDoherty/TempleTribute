package com.lunatech.bible.service;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.model.Random;
import com.lunatech.bible.repository.RandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class RandomService {

    @Autowired
    private RandomRepository randomRepository;
    public List<Random> listAll() {
        return  null;
    }

    public ResponseEntity<String> search(String book, String chapter, String verse) {
        return randomRepository.retrieveSpecificBibleVerse(book, chapter, verse);
    }

    public List<Kjv> searchRandom(int length) {
        return randomRepository.retrieveRandomBibleString(length);
    }
}
