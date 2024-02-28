package com.example.weather_app.repository;

import com.example.weather_app.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    Area findByIlIgnoreCase(String il);
}