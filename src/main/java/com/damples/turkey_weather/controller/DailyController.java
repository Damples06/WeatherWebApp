package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.model.Daily;
import com.damples.turkey_weather.service.DailyService;
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
public class DailyController {

    private final DailyService dailyService;

    // This method is used to get the 5-day weather data of the area.
    @GetMapping("/{provinceName}/daily/all")
    public ResponseEntity<List<Daily>> getAllDaily(@PathVariable String provinceName) throws IOException, InterruptedException {
        List<Daily> dailyWeatherData = dailyService.getAllDailyData(provinceName);
        return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
    }
    // This method is used to get the daily weather data of the area by index. Index starts from 1 and ends at 5.
    @GetMapping("/{provinceName}/daily/{i}")
    public ResponseEntity<Daily> getDailyByIndex(@PathVariable String provinceName, @PathVariable Integer i) throws IOException, InterruptedException {
        Daily daily = dailyService.getSingleDailyData(provinceName, i);
        return new ResponseEntity<>(daily, HttpStatus.OK);
    }
}