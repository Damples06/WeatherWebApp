package com.example.weather_app.service;

import com.example.weather_app.model.Forecast;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForecastService {
    @Autowired
    private final AreaService areaService;
    private final ObjectMapper objectMapper;
    public Forecast getForecastData(String il) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/sondurumlar?istno=" + areaService.getAreaDetails(il).getForecastId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Forecast> forecastList = objectMapper.readValue(response.body(), new TypeReference<>() {});
        Forecast forecast = forecastList.get(0);
        forecast.regularize();
        return forecast;
    }
}
