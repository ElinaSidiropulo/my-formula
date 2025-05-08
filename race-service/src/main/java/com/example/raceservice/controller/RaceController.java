package com.example.raceservice.controller;

import com.example.raceservice.dto.*;
import com.example.raceservice.model.Race;
import com.example.raceservice.service.RaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<RaceResponseDTO> getAllRaces() {
        return raceService.getAllRaces();
    }

    @PostMapping
    public Race createRace(@RequestBody RaceRequestDTO dto) {
        return raceService.createRace(dto);
    }

    @GetMapping("/{id}")
    public RaceResponseDTO getRace(@PathVariable Long id) {
        return raceService.getRaceById(id);
    }

    @GetMapping("/winner")
    public DriverResultDTO getLastWinner() {
        return raceService.getLastRaceWinner();
    }

    @DeleteMapping("/{id}")
    public void deleteRace(@PathVariable Long id) {
        raceService.deleteRace(id);
    }
}
