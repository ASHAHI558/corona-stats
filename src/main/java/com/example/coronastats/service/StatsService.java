package com.example.coronastats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coronastats.entity.Stats;
import com.example.coronastats.repository.StatsRepository;

@Service
public class StatsService {

    private final StatsRepository repository;

    @Autowired
    public StatsService(StatsRepository repository) {
        this.repository = repository;
    }

    public Stats save(Stats stats) {
        deleteIfCountryExists(stats.getCountry());
        return repository.save(stats);
    }

    public Stats findStatsByCountry(String country) {
        return repository.findStatsByCountry(country);
    }

    private void deleteIfCountryExists(String country) {
        Stats stats = findStatsByCountry(country);
        if (stats != null) {
            repository.deleteById(stats.getId());
        }
    }
}
