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
        System.out.println(response.body());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        JsonNode hourlyNode = jsonNode.get(0).get("tahmin");
        List<Hourly> hourlyList = objectMapper.readValue(hourlyNode.toString(), new TypeReference<>() {});
        for (Hourly hourly : hourlyList) {
            regularize(hourly);
        }
        return hourlyList;
    }

    private void regularize(Hourly hourly) {
        hourly.setDate(hourly.getDate().plusHours(3));
        switch (hourly.getEvent()) {
            case "A" -> hourly.setEvent("Açık");
            case "AB" -> hourly.setEvent("Az Bulutlu");
            case "PB" -> hourly.setEvent("Parçalı Bulutlu");
            case "CB" -> hourly.setEvent("Çok Bulutlu");
            case "HY" -> hourly.setEvent("Hafif Yağmurlu");
            case "Y" -> hourly.setEvent("Yağmurlu");
            case "KY" -> hourly.setEvent("Kuvvetli Yağmurlu");
            case "KKY" -> hourly.setEvent("Karla Karışık Yağmurlu");
            case "HKY" -> hourly.setEvent("Hafif Kar Yağışlı");
            case "K" -> hourly.setEvent("Kar Yağışlı");
            case "YKY" -> hourly.setEvent("Yoğun Kar Yağışlı");
            case "HSY" -> hourly.setEvent("Hafif Sağanak Yağışlı");
            case "SY" -> hourly.setEvent("Sağanak Yağışlı");
            case "KSY" -> hourly.setEvent("Kuvvetli Sağanak Yağışlı");
            case "MSY" -> hourly.setEvent("Mevzi Sağanak Yağışlı");
            case "DY" -> hourly.setEvent("Dolu");
            case "GSY" -> hourly.setEvent("Gökgürültülü Sağanak Yağışlı");
            case "KGY" -> hourly.setEvent("Kuvvetli Gökgürültülü Sağanak Yağışlı");
            case "SIS" -> hourly.setEvent("Sisli");
            case "PUS" -> hourly.setEvent("Puslu");
            case "DMN" -> hourly.setEvent("Dumanlı");
            case "KF" -> hourly.setEvent("Kum veya Toz Taşınımı");
            case "R" -> hourly.setEvent("Rüzgarlı");
            case "GKR" -> hourly.setEvent("Güneyli Kuvvetli Rüzgar");
            case "KKR" -> hourly.setEvent("Kuzeyli Kuvvetli Rüzgar");
            case "SCK" -> hourly.setEvent("Sıcak");
            case "SGK" -> hourly.setEvent("Soğuk");
            case "HHY" -> hourly.setEvent("Yağışlı");
            default -> hourly.setEvent("Bilinmeyen Durum");

        }
    }
//    public Hourly getDesiredHourlyData(String provinceName, int i) throws IOException, InterruptedException {
//        List<Hourly> hourlyList = getHourlyWeatherData(provinceName);
//        Hourly hourly;
//        switch (i) {
//            case 1 -> hourly = hourlyList.get(0);
//            case 2 -> hourly = hourlyList.get(1);
//            case 3 -> hourly = hourlyList.get(2);
//            case 4 -> hourly = hourlyList.get(3);
//            case 5 -> hourly = hourlyList.get(4);
//            case 6 -> hourly = hourlyList.get(5);
//            case 7 -> hourly = hourlyList.get(6);
//            case 8 -> hourly = hourlyList.get(7);
//            case 9 -> hourly = hourlyList.get(8);
//            case 10 -> hourly = hourlyList.get(9);
//            case 11 -> hourly = hourlyList.get(10);
//            default -> throw new IllegalStateException("Unexpected value: " + i);
//        }
//        return hourly;
//    }
}


//case "A" -> object.setEvent("Açık");
//case "AB" -> object.setEvent("Az Bulutlu");
//case "PB" -> object.setEvent("Parçalı Bulutlu");
//case "CB" -> object.setEvent("Çok Bulutlu");
//case "HY" -> object.setEvent("Hafif Yağmurlu");
//case "Y" -> object.setEvent("Yağmurlu");
//case "KY" -> object.setEvent("Kuvvetli Yağmurlu");
//case "KKY"-> object.setEvent("Karla Karışık Yağmurlu");
//case "HKY"-> object.setEvent("Hafif Kar Yağışlı");
//case "K" -> object.setEvent("Kar Yağışlı");
//case "YKY" -> object.setEvent("Yoğun Kar Yağışlı");
//case "HSY" -> object.setEvent("Hafif Sağanak Yağışlı");
//case "SY" -> object.setEvent("Sağanak Yağışlı");
//case "KSY" -> object.setEvent("Kuvvetli Sağanak Yağışlı");
//case "MSY" -> object.setEvent("Mevzi Sağanak Yağışlı");
//case "DY" -> object.setEvent("Dolu");
//case "GSY" -> object.setEvent("Gökgürültülü Sağanak Yağışlı");
//case "KGY" -> object.setEvent("Kuvvetli Gökgürültülü Sağanak Yağışlı");
//case "SIS" -> object.setEvent("Sisli");
//case "PUS" -> object.setEvent("Puslu");
//case "DMN" -> object.setEvent("Dumanlı");
//case "KF" -> object.setEvent("Kum veya Toz Taşınımı");
//case "R" -> object.setEvent("Rüzgarlı");
//case "GKR" -> object.setEvent("Güneyli Kuvvetli Rüzgar");
//case "KKR" -> object.setEvent("Kuzeyli Kuvvetli Rüzgar");
//case "SCK" -> object.setEvent("Sıcak");
//case "SGK" -> object.setEvent("Soğuk");
//case "HHY" -> object.setEvent("Yağışlı");
//default -> object.setEvent("Bilinmeyen Durum");