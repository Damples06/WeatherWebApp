package com.example.weather_app.controller;

import com.example.weather_app.model.Area;
import com.example.weather_app.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AreaController {

    private final AreaService areaService;

    // This method is used to get the details of the area from the MGM Service.
    @GetMapping("/{il}")
    public ResponseEntity<Area> getAreaDetails(@PathVariable String il) {
        Area areaDetails = areaService.getAreaDetails(il.toLowerCase());
        return new ResponseEntity<>(areaDetails, HttpStatus.OK);
    }
}