package com.lunatech.bible.service;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.repository.DailyReadingsRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@Service
@Transactional
public class DailyReadingsService {
    @Autowired
    private DailyReadingsRepository dailyReadingsRepository;

    public DailyReadingsService(DailyReadingsRepository dailyReadingsRepository) {
        this.dailyReadingsRepository = dailyReadingsRepository;
    }

    public List<Kjv> readingToday(String book) throws JSONException, SQLException, IOException {
        return dailyReadingsRepository.retrieveTodaysReading(book);
    }
    public List<Kjv> readingTomorrow() throws IOException {
        return dailyReadingsRepository.retrieveTomorrowsReading();
    }
    public List<Kjv> readingYesterday() throws IOException {
        return dailyReadingsRepository.retrieveYesterdaysReading();
    }
}
