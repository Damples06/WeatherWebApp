package com.example.weather_app.repository;

import com.example.weather_app.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO: Rename this interface to CityRepository
@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
}
