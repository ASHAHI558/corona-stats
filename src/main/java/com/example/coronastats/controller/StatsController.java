package com.example.coronastats.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.coronastats.constant.AppConstant;
import com.example.coronastats.entity.Stats;
import com.example.coronastats.service.StatsHelperService;
import com.example.coronastats.service.StatsService;

@RestController(StatsController.API)
@Slf4j
public class StatsController {

    public static final String API = "/stats";

    private final StatsService statsService;
    private final ObjectMapper objectMapper;
    private final StatsHelperService statsHelperService;

    @Autowired
    public StatsController(
        StatsService statsService,
        ObjectMapper objectMapper,
        StatsHelperService statsHelperService
    ) {
        this.statsService = statsService;
        this.objectMapper = objectMapper;
        this.statsHelperService = statsHelperService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh() {
        statsHelperService.fetchAllData();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        Object data;
        try {
            data = objectMapper.readValue(
                statsService.findStatsByCountry(AppConstant.ALL).getData(), Object.class);
            return ResponseEntity.ok(data);
        } catch (JsonProcessingException e) {
            log.error("Error getting data: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/country/{name}")
    public ResponseEntity<?> getByCountry(@PathVariable String name,
        @RequestParam(required = false) Integer limit) {
        Stats countryStats = statsService.findStatsByCountry(name);
        if (countryStats == null) {
            statsHelperService.fetchCountryData(name);
            countryStats = statsService.findStatsByCountry(name);
        }
        Object data;
        try {
            data = objectMapper.readValue(countryStats.getData(), Object.class);
            if (limit != null) {
                data = ((List<Object>) data).subList(0, limit);
            }
            return ResponseEntity.ok(data);
        } catch (JsonProcessingException e) {
            log.error("Error getting data: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
