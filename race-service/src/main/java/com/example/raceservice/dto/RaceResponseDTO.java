package com.example.raceservice.dto;

import java.time.LocalDate;
import java.util.List;

public class RaceResponseDTO {
    private Long id;
    private String location;
    private LocalDate date;
    private List<DriverResultDTO> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DriverResultDTO> getResults() {
        return results;
    }

    public void setResults(List<DriverResultDTO> results) {
        this.results = results;
    }
}
