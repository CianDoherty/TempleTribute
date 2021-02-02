package com.lunatech.bible.service;

import com.lunatech.bible.model.Kjv;
import com.lunatech.bible.repository.DailyReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class DailyReadingsService {
    @Autowired
    private DailyReadingsRepository dailyReadingsRepository;

    public DailyReadingsService(DailyReadingsRepository dailyReadingsRepository) {
        this.dailyReadingsRepository = dailyReadingsRepository;
    }

    public List<Kjv> readingToday() {
        return dailyReadingsRepository.retrieveTodaysReading();
    }
    public List<Kjv> readingTomorrow()  {
        return dailyReadingsRepository.retrieveTomorrowsReading();
    }
    public List<Kjv> readingYesterday()  {
        return dailyReadingsRepository.retrieveYesterdaysReading();
    }
}
