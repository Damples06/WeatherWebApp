package com.example.weather_app.controller;

import com.example.weather_app.model.Hourly;
import com.example.weather_app.service.HourlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class HourlyController {

    private final HourlyService hourlyService;

    @GetMapping("/{il}/hourly/all")
    public ResponseEntity<List<Hourly>> getAllHourlyData(@PathVariable String il) throws IOException, InterruptedException {
        List<Hourly> hourlyList = hourlyService.getHourlyWeatherData(il);
        return new ResponseEntity<>(hourlyList, HttpStatus.OK);
    }

    @GetMapping("/{il}/hourly/{i}")
    public ResponseEntity<Hourly> getSingleHourlyData(@PathVariable String il,@PathVariable Integer i) throws IOException, InterruptedException {
        Hourly hourly= hourlyService.getDesiredHourlyData(il, i);
        return new ResponseEntity<>(hourly, HttpStatus.OK);
    }
}
