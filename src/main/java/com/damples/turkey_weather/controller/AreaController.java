package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.model.Area;
import com.damples.turkey_weather.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    // This method is used to get the details of the area.
    @GetMapping("/{provinceName}")
    public ResponseEntity<Area> getAreaDetails(@PathVariable String provinceName) {
        Area areaDetails = areaService.getAreaDetails(provinceName.toLowerCase());
        return new ResponseEntity<>(areaDetails, HttpStatus.OK);
    }
}