package com.lunatech.bible.repository;

import com.lunatech.bible.model.Kjv;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DailyReadingsRepository {
    @Value(value = "${api.bible.API_KEY}")
    String API_KEY;

    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    protected DataSource dataSource;
    @Autowired
    public DailyReadingsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private static final Logger log = LoggerFactory.getLogger(DailyReadingsRepository.class);

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    public List<Kjv> retrieveTodaysReading() {

        Date today = new Date();

        String pattern = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dateStr = simpleDateFormat.format(today);

        List<Kjv> verseList = new ArrayList<>();
        try {
            log.info("Attempting query verse List: "+ dateStr);
            verseList = queryVerseList(dateStr);
        } catch (Exception e) {
            log.info("Received Exception during when querying verses: " + e);
        }
        return verseList;
    }

    public List<Kjv> retrieveTomorrowsReading() {
        Date tomorow = new Date(new Date().getTime() + MILLIS_IN_A_DAY);

        String pattern = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dateStr = simpleDateFormat.format(tomorow);
        List<Kjv> verseList = new ArrayList<>();
        try {
            log.info("Attempting query verse List: "+ dateStr);
            verseList = queryVerseList(dateStr);
        } catch (Exception e) {
            log.info("Received Exception during send Message: " + e);
        }
        return verseList;
    }

    public List<Kjv> retrieveYesterdaysReading() {
        Date yesterday = new Date(new Date().getTime() - MILLIS_IN_A_DAY);
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dateStr = simpleDateFormat.format(yesterday);

        List<Kjv> verseList = new ArrayList<>();
        try {
            log.info("Attempting query verse List: "+ dateStr);

            verseList = queryVerseList(dateStr);
        } catch (Exception e) {
            log.info("Received Exception during when querying verses: " + e);
        }
        return verseList;
    }

    public List<Kjv> queryVerseList(String dateStr) throws IOException {
        Document doc = null;

        //https://catholicreadings.org/
        //https://wau.org/meditations/
        // see meditations
        doc = Jsoup.connect("https://wau.org/meditations/"+dateStr).get();

        Element el = doc.select("h1.title-alt").get(0);
        String[] bis = el.text().split("[:, ,\\–,\\-,\\,]");
//       "Meditations: Hebrews 4:1-5, 11".split("[:, ,\\–,\\-,\\,]");
        String book = null, chapterStart = null, chapterEnd = null,
                verseStart = null, verseEnd = null,
                verseStart2 = null, verseEnd2 = null;
        List<Kjv>  responseList = null;
        if (bis.length == 8) {          // Format: Meditations: Hebrews 4:1-5, 11
            book = bis[2];
            chapterStart = bis[3];
            verseStart = bis[4];
            verseEnd = bis[5];
            verseStart2 = bis[7];

            Query queryBook = entityManager.createQuery("SELECT b FROM KeyEnglish WHERE n = :book");
            queryBook.setParameter("book", book);
            Integer bookId = (Integer) queryBook.getResultList().get(0);
            TypedQuery<Kjv> queryVerses = entityManager.createNamedQuery("dailyVerseQry", Kjv.class);
            queryVerses.setParameter("bookId", bookId);
            queryVerses.setParameter("chapter", chapterStart);
            queryVerses.setParameter("verseStart", Integer.valueOf(verseStart));
            queryVerses.setParameter("verseEnd", Integer.valueOf(verseEnd));
            TypedQuery<Kjv> queryVerse = entityManager.createNamedQuery("singleVerseQry", Kjv.class);
            queryVerse.setParameter("bookId", bookId);
            queryVerse.setParameter("chapter", chapterStart);
            queryVerse.setParameter("verse", Integer.valueOf(verseStart2));
            List<Kjv> list1 = queryVerses.getResultList();
            List<Kjv> list2 = queryVerse.getResultList();
            responseList =  Stream.concat(list1.stream(),list2.stream())
                    .collect(Collectors.toList());
        } else if (bis.length == 6) {   // Format: Meditations: Hebrews 4:1-5
            book = bis[2];
            chapterStart = bis[3];
            verseStart = bis[4];
            verseEnd = bis[5];

            Query queryBook = entityManager.createQuery("SELECT b FROM KeyEnglish WHERE n = :book");
            queryBook.setParameter("book", book);
            Integer bookId = (Integer) queryBook.getResultList().get(0);
            TypedQuery<Kjv> queryVerse = entityManager.createNamedQuery("dailyVerseQry", Kjv.class);
            queryVerse.setParameter("bookId", bookId);
            queryVerse.setParameter("chapter", chapterStart);
            queryVerse.setParameter("verseStart", Integer.valueOf(verseStart));
            queryVerse.setParameter("verseEnd", Integer.valueOf(verseEnd));
            responseList = queryVerse.getResultList();
        } else if (bis.length == 9) {   // Format: Meditations: Hebrews 4:1-5, 7-9
            book = bis[2];
            chapterStart = bis[3];
            verseStart = bis[4];
            verseEnd = bis[5];
            verseStart2 = bis[7];
            verseEnd2 = bis[8];

            Query queryBook = entityManager.createQuery("SELECT b FROM KeyEnglish WHERE n = :book");
            queryBook.setParameter("book", book);
            Integer bookId = (Integer) queryBook.getResultList().get(0);
            TypedQuery<Kjv> queryVerse1 = entityManager.createNamedQuery("dailyVerseQry", Kjv.class);
            queryVerse1.setParameter("bookId", bookId);
            queryVerse1.setParameter("chapter", chapterStart);
            queryVerse1.setParameter("verseStart", Integer.valueOf(verseStart));
            queryVerse1.setParameter("verseEnd", Integer.valueOf(verseEnd));
            TypedQuery<Kjv> queryVerse2 = entityManager.createNamedQuery("dailyVerseQry", Kjv.class);
            queryVerse2.setParameter("bookId", bookId);
            queryVerse2.setParameter("chapter", chapterStart);
            queryVerse2.setParameter("verseStart", Integer.valueOf(verseStart2));
            queryVerse2.setParameter("verseEnd", Integer.valueOf(verseEnd2));
            List<Kjv> list1 = queryVerse1.getResultList();
            List<Kjv> list2 = queryVerse2.getResultList();
            responseList = Stream.concat(list1.stream(),list2.stream())
                    .collect(Collectors.toList());
        } else if (bis.length == 7) {   // Format: Meditations: Hebrews 7:25–8:6
            book = bis[2];
            chapterStart = bis[3];
            chapterEnd = bis[5];
            verseStart = bis[4];
            verseEnd = bis[6];

            Query queryVerse1 = entityManager.createQuery("betweenVerse1Qry", Kjv.class);
            Query queryVerse2 = entityManager.createQuery("betweenVerse2Qry", Kjv.class);
            queryVerse1.setParameter("bookId", book);
            queryVerse2.setParameter("bookId", book);
            queryVerse1.setParameter("chapterStart", Integer.valueOf(chapterStart));
            queryVerse2.setParameter("chapterEnd", Integer.valueOf(chapterEnd));
            queryVerse1.setParameter("verseStart", Integer.valueOf(verseStart));
            queryVerse2.setParameter("verseEnd", Integer.valueOf(verseEnd));
            List<Kjv> list1 = queryVerse1.getResultList();
            List<Kjv> list2 = queryVerse2.getResultList();
            responseList = Stream.concat(list1.stream(),list2.stream())
                    .collect(Collectors.toList());
        } else {
            log.info("Error caused by unknown format during Jsoup parsing");
        }
        return responseList;
    }

}
