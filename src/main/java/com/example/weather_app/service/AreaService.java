package com.example.weather_app.service;

import com.example.weather_app.model.Area;
import com.example.weather_app.repository.AreaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public Area getAreaDetails(String il) {
        if (areaRepository.findByIlIgnoreCase(il) != null) {
            System.out.println("From DB");
            return areaRepository.findByIlIgnoreCase(il);
        } else if ("favicon.ico".equals(il)) {
            return null;
        } else {
            System.out.println("From API");
            return getAreaDetailsFromAPI(il);
        }
    }

    private Area getAreaDetailsFromAPI(String il) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/merkezler?il=" + il))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Area> areas = objectMapper.readValue(response.body(), new TypeReference<>() {});
        Area areaToSave = areas.get(0);
        areaToSave.convertToLowerCase();
        areaRepository.save(areaToSave);
        return areaToSave;
    }
}