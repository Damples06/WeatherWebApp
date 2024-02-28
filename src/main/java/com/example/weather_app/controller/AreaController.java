package com.example.weather_app.controller;

import com.example.weather_app.model.Area;
import com.example.weather_app.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/{il}")
    public ResponseEntity<Area> getAreaDetails(@PathVariable String il) {
        Area areaDetails = areaService.getAreaDetails(il);
        return new ResponseEntity<>(areaDetails, HttpStatus.OK);
    }
}