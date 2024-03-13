package com.damples.turkey_weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Forecast {
    @Id
    @JsonProperty("istNo")
    private Integer forecastId;

    @JsonProperty("sicaklik")
    private Double temperature;
    @JsonProperty("nem")
    private Integer humidity;
    @JsonProperty("ruzgarHiz")
    private Double windSpeed;
    @JsonProperty("ruzgarYon")
    private Integer windDirection;
    @JsonProperty("hadiseKodu")
    private String event;
    @JsonProperty("gorus")
    private Integer visibility;
    @JsonProperty("kapalilik")
    private Integer cloudiness;
    @JsonProperty("denizSicaklik")
    private Integer seaTemperature;
    @JsonProperty("aktuelBasinc")
    private Integer actualPressure;
    @JsonProperty("denizeIndirgenmisBasinc")
    private Double pressureReducedToSeaLevel;
    @JsonProperty("karYukseklik")
    private Integer snowHeight;
    @JsonProperty("yagis00Now")
    private Double rainNow;
    @JsonProperty("yagis10Dk")
    private Double rain10Min;
    @JsonProperty("yagis1Saat")
    private Double rain1Hour;
    @JsonProperty("yagis6Saat")
    private Double rain6Hour;
    @JsonProperty("yagis12Saat")
    private Double rain12Hour;
    @JsonProperty("yagis24Saat")
    private Double rain24Hour;
    @JsonProperty("veriZamani")
    private LocalDateTime dataTime;
    @JsonProperty("denizVeriZamani")
    private LocalDateTime seaDataTime;
}
