package com.example.raceservice.client;

import com.example.raceservice.dto.DriverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "driver-service")
public interface DriverClient {
    @GetMapping("/drivers/{id}")
    DriverDTO getDriverById(@PathVariable("id") Long id);
}
