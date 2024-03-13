package com.damples.turkey_weather.service;

import com.damples.turkey_weather.model.Forecast;
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

    public Forecast getForecastData(String provinceName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/sondurumlar?istno=" + areaService.getAreaDetails(provinceName).getForecastId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        return getForecast(client, request);
    }

    public Forecast getForecastData(String provinceName, String districtName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/sondurumlar?istno=" + areaService.getAreaDetails(provinceName, districtName).getForecastId()))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        return getForecast(client, request);
    }

    private Forecast getForecast(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Forecast> forecastList = objectMapper.readValue(response.body(), new TypeReference<>() {});
        Forecast forecast = forecastList.get(0);
        regularize(forecast);
        return forecast;
    }

    private void regularize(Forecast forecast) {
        if (forecast.getTemperature() == -9999)
            forecast.setTemperature(null);
        if (forecast.getHumidity() == -9999)
            forecast.setHumidity(null);
        if (forecast.getWindSpeed() == -9999)
            forecast.setWindSpeed(null);
        else
            forecast.setWindSpeed((double) Math.round(forecast.getWindSpeed() * 100) / 100);
        if (forecast.getWindDirection() == -9999)
            forecast.setWindDirection(null);
        if (forecast.getVisibility() == -9999)
            forecast.setVisibility(null);
        if (forecast.getCloudiness() == -9999)
            forecast.setCloudiness(null);
        if (forecast.getActualPressure() == -9999)
            forecast.setActualPressure(null);
        if (forecast.getPressureReducedToSeaLevel() == -9999)
            forecast.setPressureReducedToSeaLevel(null);
        if (forecast.getSeaTemperature() == -9999)
            forecast.setSeaTemperature(null);
        if (forecast.getSnowHeight() == -9999)
            forecast.setSnowHeight(null);
        switch (forecast.getEvent()) {
            case "A" -> forecast.setEvent("Açık");
            case "AB" -> forecast.setEvent("Az Bulutlu");
            case "PB" -> forecast.setEvent("Parçalı Bulutlu");
            case "CB" -> forecast.setEvent("Çok Bulutlu");
            case "HY" -> forecast.setEvent("Hafif Yağmurlu");
            case "Y" -> forecast.setEvent("Yağmurlu");
            case "KY" -> forecast.setEvent("Kuvvetli Yağmurlu");
            case "KKY" -> forecast.setEvent("Karla Karışık Yağmurlu");
            case "HKY" -> forecast.setEvent("Hafif Kar Yağışlı");
            case "K" -> forecast.setEvent("Kar Yağışlı");
            case "YKY" -> forecast.setEvent("Yoğun Kar Yağışlı");
            case "HSY" -> forecast.setEvent("Hafif Sağanak Yağışlı");
            case "SY" -> forecast.setEvent("Sağanak Yağışlı");
            case "KSY" -> forecast.setEvent("Kuvvetli Sağanak Yağışlı");
            case "MSY" -> forecast.setEvent("Mevzi Sağanak Yağışlı");
            case "DY" -> forecast.setEvent("Dolu");
            case "GSY" -> forecast.setEvent("Gökgürültülü Sağanak Yağışlı");
            case "KGY" -> forecast.setEvent("Kuvvetli Gökgürültülü Sağanak Yağışlı");
            case "SIS" -> forecast.setEvent("Sisli");
            case "PUS" -> forecast.setEvent("Puslu");
            case "DMN" -> forecast.setEvent("Dumanlı");
            case "KF" -> forecast.setEvent("Kum veya Toz Taşınımı");
            case "R" -> forecast.setEvent("Rüzgarlı");
            case "GKR" -> forecast.setEvent("Güneyli Kuvvetli Rüzgar");
            case "KKR" -> forecast.setEvent("Kuzeyli Kuvvetli Rüzgar");
            case "SCK" -> forecast.setEvent("Sıcak");
            case "SGK" -> forecast.setEvent("Soğuk");
            case "HHY" -> forecast.setEvent("Yağışlı");
            default -> forecast.setEvent("Bilinmeyen Durum");
        }
    }
}
