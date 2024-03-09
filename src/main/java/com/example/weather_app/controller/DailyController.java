package com.example.weather_app.controller;

import com.example.weather_app.model.Daily;
import com.example.weather_app.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping("/{il}/daily/all")
    public ResponseEntity<List<Daily>> getAllDaily(@PathVariable String il) throws IOException, InterruptedException {
        List<Daily> dailyWeatherData = dailyService.getAllDailyData(il);
        return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
    }

    @GetMapping("/{il}/daily/{i}")
    public ResponseEntity<Daily> getDailyByIndex(@PathVariable String il, @PathVariable Integer i) throws IOException, InterruptedException {
        Daily daily = dailyService.getSingleDailyData(il, i);
        return new ResponseEntity<>(daily, HttpStatus.OK);
    }
}