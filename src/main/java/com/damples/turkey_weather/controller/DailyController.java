package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.model.Daily;
import com.damples.turkey_weather.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily")
public class DailyController {

    private final DailyService dailyService;

    // This method is used to get the 5-day weather data of the area.
    @GetMapping("/all")
    public ResponseEntity<List<Daily>> getAllDaily(@RequestParam String provinceName, @RequestParam(required = false) String districtName) throws IOException, InterruptedException {
        if (districtName != null) {
            List<Daily> dailyWeatherData = dailyService.getAllDailyData(provinceName, districtName);
            return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
        } else {
            List<Daily> dailyWeatherData = dailyService.getAllDailyData(provinceName);
            return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
        }
    }
    // This method is used to get the daily weather data of the area by index. Index starts from 1 and ends at 5.
    @GetMapping("/{i}")
    public ResponseEntity<Daily> getDailyByIndex(@RequestParam String provinceName, @RequestParam(required = false) String districtName, @PathVariable Integer i) throws IOException, InterruptedException {
        if (districtName != null) {
            Daily daily = dailyService.getSingleDailyData(provinceName, districtName, i);
            return new ResponseEntity<>(daily, HttpStatus.OK);
        } else {
            Daily daily = dailyService.getSingleDailyData(provinceName, i);
            return new ResponseEntity<>(daily, HttpStatus.OK);
        }
    }
}