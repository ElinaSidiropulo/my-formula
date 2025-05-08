package com.example.teamservice.service;

import com.example.teamservice.client.DriverClient;
import com.example.teamservice.dto.DriverDTO;
import com.example.teamservice.dto.TeamWithDriversDTO;
import com.example.teamservice.model.Team;
import com.example.teamservice.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final DriverClient driverClient;

    @Autowired
    public TeamService(TeamRepository teamRepository, DriverClient driverClient) {
        this.teamRepository = teamRepository;
        this.driverClient = driverClient;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
    public TeamWithDriversDTO getTeamWithDriversById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        System.out.println("Driver IDs from team: " + team.getDriverIds());

        List<DriverDTO> drivers = team.getDriverIds().stream()
                .map(driverId -> {
                    try {
                        DriverDTO driver = driverClient.getDriverById(driverId);
                        System.out.println("Fetched driver " + driverId + ": " + driver);
                        return driver;
                    } catch (Exception e) {
                        System.err.println("Failed to fetch driver with ID " + driverId + ": " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        TeamWithDriversDTO teamWithDriversDTO = new TeamWithDriversDTO();
        teamWithDriversDTO.setId(team.getId());
        teamWithDriversDTO.setName(team.getName());
        teamWithDriversDTO.setCountry(team.getCountry());
        teamWithDriversDTO.setDrivers(drivers);

        return teamWithDriversDTO;
    }

}
