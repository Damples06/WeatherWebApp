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
    public ResponseEntity<Area> getAreaDetails(@RequestParam String province,
                                               @RequestParam(required = false) String district) {
        Area areaDetails;
        if (district != null) {
            areaDetails = areaService.getAreaDetails(province.toLowerCase(), district.toLowerCase());
        } else {
            areaDetails = areaService.getAreaDetails(province.toLowerCase());
        }
        return new ResponseEntity<>(areaDetails, HttpStatus.OK);
    }
}