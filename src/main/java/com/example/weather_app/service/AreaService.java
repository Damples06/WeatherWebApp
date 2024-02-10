package com.example.weather_app.service;

//TODO: Rename this interface to CityService
public interface AreaService {
    String getProvinceDetailsByName(String name) throws Exception;

//    String getDistrictDetailsByName(String provinceName, String districtName) throws Exception;

    String getWeatherDetailsByProvinceName(String name) throws Exception;

    String getDegreeByProvinceName(String name) throws Exception;

    String getWindSpeedByName(String name) throws Exception;

    String getWindDirectionByName(String name) throws Exception;

    String getHumidityByName(String name) throws Exception;

    String getMerkezIDByProvinceName(String name) throws Exception;

}
