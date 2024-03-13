package com.damples.turkey_weather.controller;

import com.damples.turkey_weather.model.Area;
import com.damples.turkey_weather.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {

    private final AreaService areaService;

    // This method is used to get the details of the area.
    @GetMapping()
    public ResponseEntity<Area> getAreaDetails(@RequestParam String provinceName,
                                               @RequestParam(required = false) String districtName) {
        if (districtName != null) {
            Area areaDetails = areaService.getAreaDetails(provinceName.toLowerCase(), districtName.toLowerCase());
            return new ResponseEntity<>(areaDetails, HttpStatus.OK);
        } else {
            Area areaDetails = areaService.getAreaDetails(provinceName.toLowerCase());
            return new ResponseEntity<>(areaDetails, HttpStatus.OK);
        }
    }
}