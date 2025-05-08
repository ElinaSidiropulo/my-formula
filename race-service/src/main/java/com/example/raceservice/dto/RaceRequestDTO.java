package com.example.raceservice.dto;

import java.time.LocalDate;
import java.util.List;

public class RaceRequestDTO {
    private String location;
    private LocalDate date;
    private List<ResultDTO> results;

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

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }
}
