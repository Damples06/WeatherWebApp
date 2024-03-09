package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.service.HourlyService;
import com.damples.turkey_weather.model.Hourly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{provinceName}/hourly")
public class HourlyController {

    private final HourlyService hourlyService;

    // This method is used to get the hourly weather data of the area.
    @GetMapping("/all")
    public ResponseEntity<List<Hourly>> getAllHourlyData(@PathVariable String provinceName) throws IOException, InterruptedException {
        List<Hourly> hourlyList = hourlyService.getHourlyWeatherData(provinceName);
        return new ResponseEntity<>(hourlyList, HttpStatus.OK);
    }

    // This method is used to get the hourly weather data of the area by index. Index starts from 1 and ends at 11.
    @GetMapping("/{i}")
    public ResponseEntity<Hourly> getSingleHourlyData(@PathVariable String provinceName, @PathVariable Integer i) throws IOException, InterruptedException {
        Hourly hourly= hourlyService.getDesiredHourlyData(provinceName, i);
        return new ResponseEntity<>(hourly, HttpStatus.OK);
    }
}
