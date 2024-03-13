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
        forecast.setWindSpeed((double) Math.round(forecast.getWindSpeed() * 100) / 100);
        switch (forecast.getEvent()) {
            case "A":
                forecast.setEvent("Açık");
                break;
            case "AB":
                forecast.setEvent("Az Bulutlu");
                break;
            case "PB":
                forecast.setEvent("Parçalı Bulutlu");
                break;
            case "CB":
                forecast.setEvent("Çok Bulutlu");
                break;
            case "HY":
                forecast.setEvent("Hafif Yağmurlu");
                break;
            case "Y":
                forecast.setEvent("Yağmurlu");
                break;
            case "KY":
                forecast.setEvent("Kuvvetli Yağmurlu");
                break;
            case "KKY":
                forecast.setEvent("Karla Karışık Yağmurlu");
                break;
            case "HKY":
                forecast.setEvent("Hafif Kar Yağışlı");
                break;
            case "K":
                forecast.setEvent("Kar Yağışlı");
                break;
            case "YKY":
                forecast.setEvent("Yoğun Kar Yağışlı");
                break;
            case "HSY":
                forecast.setEvent("Hafif Sağanak Yağışlı");
                break;
            case "SY":
                forecast.setEvent("Sağanak Yağışlı");
                break;
            case "KSY":
                forecast.setEvent("Kuvvetli Sağanak Yağışlı");
                break;
            case "MSY":
                forecast.setEvent("Mevzi Sağanak Yağışlı");
                break;
            case "DY":
                forecast.setEvent("Dolu");
                break;
            case "GSY":
                forecast.setEvent("Gökgürültülü Sağanak Yağışlı");
                break;
            case "KGY":
                forecast.setEvent("Kuvvetli Gökgürültülü Sağanak Yağışlı");
                break;
            case "SIS":
                forecast.setEvent("Sisli");
                break;
            case "PUS":
                forecast.setEvent("Puslu");
                break;
            case "DMN":
                forecast.setEvent("Dumanlı");
                break;
            case "KF":
                forecast.setEvent("Kum Fırtınası");
                break;
            case "R":
                forecast.setEvent("Rüzgarlı");
                break;
            case "GKR":
                // Güneyli olması lazım. Kontrol et.
                forecast.setEvent("Kuzeyli Kuvvetli Rüzgar");
                break;
            case "KKR":
                forecast.setEvent("Kuzeyli Kuvvetli Rüzgar");
                break;
            case "SCK":
                forecast.setEvent("Sıcak");
                break;
            case "SGK":
                forecast.setEvent("Soğuk");
                break;
            case "HHY":
                forecast.setEvent("Yağışlı");
                break;
            default:
                forecast.setEvent("Bilinmeyen");
                break;
        }

    }
}
