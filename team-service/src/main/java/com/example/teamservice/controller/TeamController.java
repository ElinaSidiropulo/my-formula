package com.example.teamservice.controller;

import com.example.teamservice.dto.TeamWithDriversDTO;
import com.example.teamservice.model.Team;
import com.example.teamservice.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamWithDriversDTO> getTeamWithDriversById(@PathVariable Long id) {
        TeamWithDriversDTO teamWithDriversDTO = teamService.getTeamWithDriversById(id);
        return ResponseEntity.ok(teamWithDriversDTO);


    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.createTeam(team));
    }
}
