package com.example.weather_app.service;

import com.example.weather_app.model.Daily5;
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
public class Daily5Service {
    @Autowired
    private final AreaService areaService;
    private final ObjectMapper objectMapper;

    public Daily5 daily5Data(String il) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/tahminler/gunluk?istno=" + areaService.getAreaDetails(il).getDailyId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Daily5> daily5List = objectMapper.readValue(response.body(), new TypeReference<>() {});
        Daily5 daily5 = daily5List.get(0);
        System.out.println(daily5.getDailyId());
        return daily5;
    }
}
