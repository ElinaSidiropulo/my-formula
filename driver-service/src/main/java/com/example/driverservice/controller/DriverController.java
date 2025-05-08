package com.example.driverservice.controller;

import com.example.driverservice.model.Driver;
import com.example.driverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Получить список всех гонщиков
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Добавить нового гонщика
    @PostMapping
    public Driver addDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    // Получить информацию о гонщике по ID
    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return driverRepository.findById(id).orElse(null); // Можно добавить обработку ошибок, если не найдено
    }
}
