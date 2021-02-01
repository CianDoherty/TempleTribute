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
//    @Value(value = "${api.bible.URI_VERSE}")
//    final String URI_VERSE;
//
//    public RandomRepository(String uri_verse) {
//        URI_VERSE = uri_verse;
//    }

    public List<Kjv> retrieveRandomBibleString(int length) {
        //String queryRandStr = "select o from Kjv o order by rand()";
        Query queryVerse = entityManager.createNamedQuery("randomVerseQry", Kjv.class);
        List<Kjv> responseList  = (List<Kjv>)queryVerse.setMaxResults(length).getResultList();

        return responseList;
    }

    public ResponseEntity<String>  retrieveSpecificBibleVerse(String bibleVersionID, String chapterID, String bibleVerseID) {

//        final String uri = `https://api.scripture.api.bible/v1/bibles/${bibleVersionID}/verses/${bibleVerseID}?include-chapter-numbers=false&include-verse-numbers=false`;
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//        final String uri = "http://localhost:8080/springrestexample/employees/{id}";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("bibleVersionID", bibleVersionID);
        params.put("bibleVerseID", bibleVerseID);
        params.put("chapterID", chapterID);
        //MRK.1
        //JHN
        //LUK

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("api-key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        final String URI_VERSE = "https://api.scripture.api.bible/v1/bibles/{bibleVersionID}/chapters/{bibleVerseID}?include-chapter-numbers=true&include-verse-numbers=true";

        ResponseEntity<String> response = restTemplate.exchange(URI_VERSE, HttpMethod.GET, entity, String.class, params);

        // Random result = restTemplate.getForObject(uri, Random.class, params);
        // System.out.println(result);
        return response;
    }
}
//    function getSelectedVerse(bibleVersionID, bibleVerseID) {
//        return new Promise((resolve, reject) => {
//    const xhr = new XMLHttpRequest();
//            xhr.withCredentials = false;
//            xhr.addEventListener(`readystatechange`, function () {
//                if (this.readyState === this.DONE) {
//        const response = JSON.parse(this.responseText);
//        const fumsId = response.meta.fumsId;
//        const { content, reference } = response.data;
//        const verse = { content, reference };
//                    _BAPI.t(fumsId);
//                    resolve(verse);
//                }
//            });
//            xhr.open(
//                    `GET`,
//                    `https://api.scripture.api.bible/v1/bibles/${bibleVersionID}/verses/${bibleVerseID}?include-chapter-numbers=false&include-verse-numbers=false`
//    );
//            xhr.setRequestHeader(`api-key`, API_KEY);
//            xhr.onerror = () => reject(xhr.statusText);
//            xhr.send();
//        });
//    }