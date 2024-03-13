package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.service.ForecastService;
import com.damples.turkey_weather.model.Forecast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastService forecastService;

    // This method is used to get the forecast data of the area.
    @GetMapping()
    public ResponseEntity<Forecast> getForecastData(@RequestParam String provinceName, @RequestParam(required = false) String districtName) throws IOException, InterruptedException {
        Forecast forecast;
        if (districtName != null) {
            forecast = forecastService.getForecastData(provinceName, districtName);
        } else {
            forecast = forecastService.getForecastData(provinceName);
        }
        return new ResponseEntity<>(forecast, HttpStatus.OK);
    }
}
