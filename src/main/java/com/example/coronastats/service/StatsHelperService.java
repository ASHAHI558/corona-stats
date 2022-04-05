package com.example.coronastats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coronastats.constant.AppConstant;
import com.example.coronastats.entity.Stats;
import com.example.coronastats.rapidapi.RapidApiController;
import com.example.coronastats.rapidapi.RapidApiResponseDto;

@Service
@Slf4j
public class StatsHelperService {

    private final StatsService statsService;
    private final RapidApiController rapidApiController;
    private final ObjectMapper objectMapper;

    @Autowired
    public StatsHelperService(
        StatsService statsService,
        RapidApiController rapidApiController,
        ObjectMapper objectMapper
    ) {
        this.statsService = statsService;
        this.rapidApiController = rapidApiController;
        this.objectMapper = objectMapper;
    }

    public void fetchAllData() {
        RapidApiResponseDto rapidApiResponseDto = rapidApiController.getAllCountriesData();
        Stats stats = new Stats();
        stats.setCountry(AppConstant.ALL);
        try {
            stats.setData(
                objectMapper.writeValueAsString(rapidApiResponseDto.getData().getCovid19Stats()));
            statsService.save(stats);
        } catch (JsonProcessingException e) {
            log.error("Error saving all countries data {}", e.getMessage());
        }
    }

    public void fetchCountryData(String country) {
        RapidApiResponseDto rapidApiResponseDto = rapidApiController.getDataByCountry(country);
        Stats stats = new Stats();
        stats.setCountry(country);
        try {
            stats.setData(
                objectMapper.writeValueAsString(rapidApiResponseDto.getData().getCovid19Stats()));
            statsService.save(stats);
        } catch (JsonProcessingException e) {
            log.error("Error saving country {} data {}", country, e.getMessage());
        }
    }
}
