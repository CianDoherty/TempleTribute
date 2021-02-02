package com.lunatech.bible.repository;

import com.lunatech.bible.model.Kjv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
public class RandomRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    @Value(value = "${api.bible.API_KEY}")
    String API_KEY;

    public RandomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Kjv> retrieveRandomBibleString(int length) {
        Query queryVerse = entityManager.createNamedQuery("randomVerseQry", Kjv.class);
        List<Kjv> responseList  = queryVerse.setMaxResults(length).getResultList();

        return responseList;
    }

    public ResponseEntity<String>  retrieveSpecificBibleVerse(String bibleVersionID, String chapterID, String bibleVerseID) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("bibleVersionID", bibleVersionID);
        params.put("bibleVerseID", bibleVerseID);
        params.put("chapterID", chapterID);
        //MRK.1
        //JHN
        //LUK

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("api-key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        final String URI_VERSE = "https://api.scripture.api.bible/v1/bibles/{bibleVersionID}/chapters/{bibleVerseID}?include-chapter-numbers=true&include-verse-numbers=true";

        ResponseEntity<String> response = restTemplate.exchange(URI_VERSE, HttpMethod.GET, entity, String.class, params);

        return response;
    }
}