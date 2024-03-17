package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.service.HourlyService;
import com.damples.turkey_weather.model.Hourly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hourly")
public class HourlyController {

    private final HourlyService hourlyService;

    // This method is used to get the hourly weather data of the area.
    @GetMapping("/all")
    public ResponseEntity<List<Hourly>> getAllHourlyData(@RequestParam String province) throws IOException, InterruptedException {
        List<Hourly> hourlyList = hourlyService.getHourlyWeatherData(province);
        return new ResponseEntity<>(hourlyList, HttpStatus.OK);
    }

    // This method is used to get the hourly weather data of the area by index. Index starts from 1 and ends at 11.
    @GetMapping("/{i}")
    public ResponseEntity<Hourly> getSingleHourlyData(@RequestParam String province,
                                                      @PathVariable Integer i) {
        Hourly hourly= hourlyService.getSingleHourlyData(province, i);
        return new ResponseEntity<>(hourly, HttpStatus.OK);
    }
}
