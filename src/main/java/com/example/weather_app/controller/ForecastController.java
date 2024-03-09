package com.example.weather_app.controller;

import com.example.weather_app.model.Forecast;
import com.example.weather_app.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ForecastController {
    private final ForecastService forecastService;

    @GetMapping("/{il}/forecast")
    public ResponseEntity<Forecast> getForecastData(@PathVariable String il) throws IOException, InterruptedException {
        Forecast forecast = forecastService.getForecastData(il);
        return new ResponseEntity<>(forecast, HttpStatus.OK);
    }
}
