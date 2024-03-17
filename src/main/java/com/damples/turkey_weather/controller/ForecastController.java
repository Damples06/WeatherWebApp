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
    public ResponseEntity<Forecast> getForecastData(@RequestParam String province,
                                                    @RequestParam(required = false) String district) throws IOException, InterruptedException {
        Forecast forecast;
        if (district != null) {
            forecast = forecastService.getForecastData(province, district);
            return new ResponseEntity<>(forecast, HttpStatus.OK);
        } else {
            forecast = forecastService.getForecastData(province);
            return new ResponseEntity<>(forecast, HttpStatus.OK);
        }
    }
}
