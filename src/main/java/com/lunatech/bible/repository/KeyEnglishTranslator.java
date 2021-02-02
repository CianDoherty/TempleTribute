package com.lunatech.bible.repository;

import com.lunatech.bible.model.KeyEnglish;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class KeyEnglishTranslator {
    @PersistenceContext
    private final EntityManager entityManager;

    public KeyEnglishTranslator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<KeyEnglish> findAll() {
        TypedQuery<KeyEnglish> queryKeyEnglish = entityManager.createNamedQuery("findAllQry", KeyEnglish.class);
        List<KeyEnglish> responseList = queryKeyEnglish.getResultList();
        return responseList;
    }
}
