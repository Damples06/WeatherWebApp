package com.example.weather_app.service;

import com.example.weather_app.model.Area;
import com.example.weather_app.model.Daily;
import com.example.weather_app.repository.AreaRepository;
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
public class DailyService {

    @Autowired
    private final AreaRepository areaRepository;
    private final ObjectMapper objectMapper;

    public Daily getDailyWeatherData(String il) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/sondurumlar?merkezid=" + getMerkezIdByIl(il)))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Daily> dailies = objectMapper.readValue(response.body(), new TypeReference<List<Daily>>() {});

        if (!dailies.isEmpty()) {
            Daily daily = dailies.get(0);
            daily.setIl(il); // Set the il field based on the input parameter
            return daily;
        } else {
            throw new RuntimeException("No daily weather data found for the given il: " + il);
        }
    }

    private int getMerkezIdByIl(String il) {
        return areaRepository.findByIlIgnoreCase(il).getMerkezId();
    }
}