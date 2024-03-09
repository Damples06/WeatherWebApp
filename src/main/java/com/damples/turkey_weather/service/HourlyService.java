package com.damples.turkey_weather.service;

import com.damples.turkey_weather.model.Hourly;
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

    public List<Hourly> getHourlyWeatherData(String provinceName) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/tahminler/saatlik?istno=" + areaService.getAreaDetails(provinceName).getHourlyId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        JsonNode hourlyNode = jsonNode.get(0).get("tahmin");
        List<Hourly> hourlyList = objectMapper.readValue(hourlyNode.toString(), new TypeReference<>() {});
        for (Hourly hourly : hourlyList) {
            hourly.regularize();
            hourly.setHourlyId(areaService.getAreaDetails(provinceName).getHourlyId());
        }
        return hourlyList;
    }

    public Hourly getDesiredHourlyData(String provinceName, int i) throws IOException, InterruptedException {
        List<Hourly> hourlyList = getHourlyWeatherData(provinceName);
        Hourly hourly;
        switch (i) {
            case 1 -> hourly = hourlyList.get(0);
            case 2 -> hourly = hourlyList.get(1);
            case 3 -> hourly = hourlyList.get(2);
            case 4 -> hourly = hourlyList.get(3);
            case 5 -> hourly = hourlyList.get(4);
            case 6 -> hourly = hourlyList.get(5);
            case 7 -> hourly = hourlyList.get(6);
            case 8 -> hourly = hourlyList.get(7);
            case 9 -> hourly = hourlyList.get(8);
            case 10 -> hourly = hourlyList.get(9);
            case 11 -> hourly = hourlyList.get(10);
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }
        return hourly;
    }
}
