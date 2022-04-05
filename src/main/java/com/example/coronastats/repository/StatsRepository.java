package com.example.coronastats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coronastats.entity.Stats;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    Stats findStatsByCountry(String country);
}
