package com.example.teamservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    @ElementCollection
    private List<Long> driverIds;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Long> getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(List<Long> driverIds) {
        this.driverIds = driverIds;
    }
}
