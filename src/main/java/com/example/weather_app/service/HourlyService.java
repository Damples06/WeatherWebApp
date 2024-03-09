package com.example.weather_app.service;

import com.example.weather_app.model.Hourly;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HourlyService {

    private final AreaService areaService;
    private final ObjectMapper objectMapper;

    public List<Hourly> getHourlyWeatherData(String cityName) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/tahminler/saatlik?istno=" + areaService.getAreaDetails(cityName).getHourlyId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        JsonNode hourlyNode = jsonNode.get(0).get("tahmin");
        List<Hourly> hourlyList = objectMapper.readValue(hourlyNode.toString(), new TypeReference<>() {});
        for (Hourly hourly : hourlyList) {
            hourly.regularize();
            hourly.setHourlyId(areaService.getAreaDetails(cityName).getHourlyId());
        }
        return hourlyList;
    }

    public Hourly getDesiredHourlyData(String cityName, int i) throws IOException, InterruptedException {
        List<Hourly> hourlyList = getHourlyWeatherData(cityName);
        return hourlyList.get(i-1);
    }
}
