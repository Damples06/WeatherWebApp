package com.example.weather_app.serviceImpl;

import com.example.weather_app.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
//TODO: Rename this class to CityServiceImpl
public class AreaServiceImpl implements AreaService {

    private final HttpClient client = HttpClient.newBuilder().build();
    
    public String getProvinceDetailsByName(String provinceName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/merkezler?il=" + provinceName))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
//TODO: Move this method to a new service called DistrictService
//    public String getDistrictDetailsByName(String provinceName, String districtName) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://servis.mgm.gov.tr/web/merkezler?il=" + provinceName + "&ilce=" + districtName))
//                .header("content-type", "application/octet-stream")
//                .header("Origin", "https://www.mgm.gov.tr")
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        return response.body();
//    }
    
    @Override
    public String getWeatherDetailsByProvinceName(String provinceName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://servis.mgm.gov.tr/web/sondurumlar?merkezid=" + getMerkezIDByProvinceName(provinceName)))
                .header("content-type", "application/octet-stream")
                .header("Origin", "https://www.mgm.gov.tr")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public String getDegreeByProvinceName(String provinceName) throws IOException, InterruptedException {
        return getStringValue(getWeatherDetailsByProvinceName(provinceName), "sicaklik");
    }

    @Override
    public String getMerkezIDByProvinceName(String provinceName) throws IOException, InterruptedException {
        return getStringValue(getProvinceDetailsByName(provinceName), "merkezId");
    }

    @Override
    public String getWindSpeedByName(String provinceName) throws IOException, InterruptedException {
        return getStringValue(getWeatherDetailsByProvinceName(provinceName), "ruzgarHiz");
    }

    @Override
    public String getWindDirectionByName(String provinceName) throws IOException, InterruptedException {
        return getStringValue(getWeatherDetailsByProvinceName(provinceName), "ruzgarYon");
    }

    @Override
    public String getHumidityByName(String provinceName) throws IOException, InterruptedException {
        return getStringValue(getWeatherDetailsByProvinceName(provinceName), "nem");
    }

    private String getStringValue(String jsonString, String key) {
        int index = jsonString.indexOf("\"" + key + "\":") + key.length() + 3;
        int endIndex = jsonString.indexOf(",", index);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", index);
        }
        return jsonString.substring(index, endIndex).replace("\"", "").trim();
    }
    
}
