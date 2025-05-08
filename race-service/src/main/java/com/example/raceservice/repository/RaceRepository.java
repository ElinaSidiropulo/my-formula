package com.example.raceservice.repository;

import com.example.raceservice.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
    Race findTopByOrderByIdDesc(); // <--- новая сигнатура
}
