package com.lunatech.bible.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.util.Random;

@Repository
public class OracleRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public OracleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String retrieveWord() {
        Query queryRandVerse = entityManager.createQuery("select t from Kjv ORDER BY RAND()");

        String verse = (String) queryRandVerse.setMaxResults(1).getResultList().get(0);
        String[] words = verse.split("[:, ,\\–,\\-,\\,!,;,]");
        int rnd = new Random().nextInt(words.length);
        return words[rnd];
    }
}
