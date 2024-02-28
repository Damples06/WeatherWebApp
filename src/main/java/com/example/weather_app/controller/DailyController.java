package com.example.weather_app.controller;

import com.example.weather_app.model.Daily;
import com.example.weather_app.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;

    @GetMapping("/daily/{il}")
    public ResponseEntity<Daily> getDailyWeatherData(@PathVariable String il) throws IOException, InterruptedException {
        Daily dailyWeatherData = dailyService.getDailyWeatherData(il);
        return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
    }
}