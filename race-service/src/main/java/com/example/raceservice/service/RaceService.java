package com.example.raceservice.service;

import com.example.raceservice.client.DriverClient;
import com.example.raceservice.dto.*;
import com.example.raceservice.model.*;
import com.example.raceservice.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final DriverClient driverClient;

    public RaceService(RaceRepository raceRepository, DriverClient driverClient) {
        this.raceRepository = raceRepository;
        this.driverClient = driverClient;
    }

    public Race createRace(RaceRequestDTO dto) {
        Race race = new Race();
        race.setLocation(dto.getLocation());
        race.setDate(dto.getDate());

        List<RaceResult> results = dto.getResults().stream()
                .map(r -> {
                    RaceResult result = new RaceResult();
                    result.setDriverId(r.getDriverId());
                    result.setPosition(r.getPosition());
                    result.setRace(race);
                    return result;
                }).collect(Collectors.toList());

        race.setResults(results);
        return raceRepository.save(race);
    }

    public RaceResponseDTO getRaceById(Long id) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        List<DriverResultDTO> resultDTOs = race.getResults().stream()
                .map(r -> {
                    DriverDTO driver = driverClient.getDriverById(r.getDriverId());
                    DriverResultDTO dto = new DriverResultDTO();
                    dto.setDriverId(driver.getId());
                    dto.setFullName(driver.getFullName());
                    dto.setPosition(r.getPosition());
                    return dto;
                }).collect(Collectors.toList());

        RaceResponseDTO response = new RaceResponseDTO();
        response.setId(race.getId());
        response.setLocation(race.getLocation());
        response.setDate(race.getDate());
        response.setResults(resultDTOs);

        return response;
    }

    public DriverResultDTO getLastRaceWinner() {
        Race lastRace = raceRepository.findTopByOrderByIdDesc();

        if (lastRace == null) throw new RuntimeException("No races found");

        RaceResult winner = lastRace.getResults().stream()
                .min((r1, r2) -> Integer.compare(r1.getPosition(), r2.getPosition()))
                .orElseThrow(() -> new RuntimeException("No results in last race"));

        DriverDTO driver = driverClient.getDriverById(winner.getDriverId());

        DriverResultDTO dto = new DriverResultDTO();
        dto.setDriverId(driver.getId());
        dto.setFullName(driver.getFullName());
        dto.setPosition(winner.getPosition());
        return dto;
    }

    public List<RaceResponseDTO> getAllRaces() {
        List<Race> races = raceRepository.findAll();

        return races.stream()
                .map(race -> {
                    List<DriverResultDTO> resultDTOs = race.getResults().stream()
                            .map(result -> {
                                DriverDTO driver = driverClient.getDriverById(result.getDriverId());
                                DriverResultDTO dto = new DriverResultDTO();
                                dto.setDriverId(driver.getId());
                                dto.setFullName(driver.getFullName());
                                dto.setPosition(result.getPosition());
                                return dto;
                            }).collect(Collectors.toList());

                    RaceResponseDTO response = new RaceResponseDTO();
                    response.setId(race.getId());
                    response.setLocation(race.getLocation());
                    response.setDate(race.getDate());
                    response.setResults(resultDTOs);
                    return response;
                })
                .collect(Collectors.toList());
    }

    public void deleteRace(Long id) {
        if (!raceRepository.existsById(id)) {
            throw new RuntimeException("Race not found with ID: " + id);
        }
        raceRepository.deleteById(id);
    }

}
