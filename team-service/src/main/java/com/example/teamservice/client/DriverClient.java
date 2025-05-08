package com.example.teamservice.client;

import com.example.teamservice.dto.DriverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "driver-service")
public interface DriverClient {
    @GetMapping("/drivers/{id}")
    DriverDTO getDriverById(@PathVariable("id") Long id);
}
