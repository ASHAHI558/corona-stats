package com.example.coronastats.config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.coronastats.service.StatsHelperService;

@Component
@Slf4j
public class Initializer {

    private final StatsHelperService statsHelperService;

    @Autowired
    public Initializer(StatsHelperService statsHelperService) {
        this.statsHelperService = statsHelperService;
    }

    @PostConstruct
    public void init() {
        log.info("Initialized and fetching data.");
        statsHelperService.fetchAllData();
    }

    @Scheduled(fixedRate = 1000 * 30)
    public void autoRefresh() {
        log.info("Polling to refresh the data.");
        statsHelperService.fetchAllData();
    }
}
