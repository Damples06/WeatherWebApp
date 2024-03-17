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

    @GetMapping("/all")
    public ResponseEntity<List<Daily>> getAllDaily(@RequestParam String province,
                                                   @RequestParam(required = false) String district) throws IOException, InterruptedException {
        List<Daily> dailyWeatherData;
        if (district != null) {
            dailyWeatherData = dailyService.getAllDailyData(province, district);
            return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
        } else {
            dailyWeatherData = dailyService.getAllDailyData(province);
            return new ResponseEntity<>(dailyWeatherData, HttpStatus.OK);
        }
    }
    @GetMapping("/{i}")
    public ResponseEntity<Daily> getDailyByIndex(@RequestParam String province,
                                                 @RequestParam(required = false) String district,
                                                 @PathVariable Integer i) throws IOException, InterruptedException {
        Daily daily;
        if (district != null) {
            daily = dailyService.getSingleDailyData(province, district, i);
        } else {
            daily = dailyService.getSingleDailyData(province, i);
        }
        return new ResponseEntity<>(daily, HttpStatus.OK);
    }
}