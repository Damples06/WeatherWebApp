package com.example.weather_app.controller;

import com.example.weather_app.serviceImpl.AreaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class AreaController {
    private final AreaServiceImpl cityServiceImpl;

    public AreaController(final AreaServiceImpl cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @GetMapping("/{provinceName}")
    private ResponseEntity<String> getWeatherDetailsByProvinceName(@PathVariable String provinceName) throws IOException, InterruptedException {
        String degree = cityServiceImpl.getDegreeByProvinceName(provinceName);
        String windSpeed = cityServiceImpl.getWindSpeedByName(provinceName);
        String windDirection = cityServiceImpl.getWindDirectionByName(provinceName);
        String humidity = cityServiceImpl.getHumidityByName(provinceName);
        String response = "Şehir: " + provinceName + "\n" +
                "Derece: " + degree + "\n" +
                "Rüzgar Hızı: " + windSpeed + "\n" +
                "Rüzgar Yönü: " + windDirection + "\n" +
                "Nem: " + humidity + "\n";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{provinceName}/{districtName}")
    private ResponseEntity<String> getWeatherDetailsByDistrictName(@PathVariable String provinceName, @PathVariable String districtName) throws IOException, InterruptedException {
        String degree = cityServiceImpl.getDegreeByProvinceName(provinceName);
        String windSpeed = cityServiceImpl.getWindSpeedByName(provinceName);
        String windDirection = cityServiceImpl.getWindDirectionByName(provinceName);
        String humidity = cityServiceImpl.getHumidityByName(provinceName);
        String response = "Şehir: " + provinceName + "\n" +
                "İlçe: " + districtName + "\n" +
                "Derece: " + degree + "\n" +
                "Rüzgar Hızı: " + windSpeed + "\n" +
                "Rüzgar Yönü: " + windDirection + "\n" +
                "Nem: " + humidity + "\n";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
