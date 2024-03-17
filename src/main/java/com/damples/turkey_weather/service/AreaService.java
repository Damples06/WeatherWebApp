package com.damples.turkey_weather.service;

import com.damples.turkey_weather.repository.AreaRepository;
import com.damples.turkey_weather.model.Area;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final ObjectMapper objectMapper;

    // This method is used to get the details of the area from the database if it exists, otherwise it gets the details from the MGM Service.
    @SneakyThrows
    public Area getAreaDetails(String provinceName) {
        if (areaRepository.findByProvinceNameAndPriority(provinceName, 1) != null) {
            return areaRepository.findByProvinceNameAndPriority(provinceName, 1);
        } else {
            return getAreaDetailsFromAPI(provinceName);
        }
    }

    @SneakyThrows
    public Area getAreaDetails(String provinceName, String districtName) {
        if (areaRepository.findByProvinceNameAndDistrictName(provinceName, districtName) != null) {
            return areaRepository.findByProvinceNameAndDistrictName(provinceName, districtName);
        } else {
            return getAreaDetailsFromAPI(provinceName, districtName);
        }
    }

    // This method is used to get the details of the area from the MGM Service.
    private Area getAreaDetailsFromAPI(String provinceName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/merkezler?il=" + provinceName))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        return getArea(client, request);
    }

    private Area getAreaDetailsFromAPI(String provinceName, String districtName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/merkezler?il=" + provinceName + "&ilce=" + districtName))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        return getArea(client, request);
    }

    private Area getArea(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Area> areas = objectMapper.readValue(response.body(), new TypeReference<>() {});
        Area areaToSave = areas.get(0);
        regularize(areaToSave);
        areaRepository.save(areaToSave);
        return areaToSave;
    }

    private void regularize(Area area) {
        area.setProvinceName(area.getProvinceName().toLowerCase());
        area.setDistrictName(area.getDistrictName().toLowerCase());
        area.setLongitude((double) Math.round(area.getLongitude() * 10000) / 10000);
        area.setLatitude((double) Math.round(area.getLatitude() * 10000) / 10000);
    }

}