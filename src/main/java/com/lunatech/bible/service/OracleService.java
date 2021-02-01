package com.lunatech.bible.service;

import com.lunatech.bible.repository.OracleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OracleService {

    @Autowired
    private OracleRepository oracleRepository;

    public String oracle() {
        return oracleRepository.retrieveWord();
    }
}
