package com.example.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Daily5 {
    @Id
    @JsonProperty("istNo")
    private Integer dailyId;

    @JsonProperty("enDusukGun1")
    private Integer minTemperatureDay1;
    @JsonProperty("enDusukGun2")
    private Integer minTemperatureDay2;
    @JsonProperty("enDusukGun3")
    private Integer minTemperatureDay3;
    @JsonProperty("enDusukGun4")
    private Integer minTemperatureDay4;
    @JsonProperty("enDusukGun5")
    private Integer minTemperatureDay5;

    @JsonProperty("enDusukNemGun1")
    private Integer minHumidityDay1;
    @JsonProperty("enDusukNemGun2")
    private Integer minHumidityDay2;
    @JsonProperty("enDusukNemGun3")
    private Integer minHumidityDay3;
    @JsonProperty("enDusukNemGun4")
    private Integer minHumidityDay4;
    @JsonProperty("enDusukNemGun5")
    private Integer minHumidityDay5;

    @JsonProperty("enYuksekGun1")
    private Integer maxTemperatureDay1;
    @JsonProperty("enYuksekGun2")
    private Integer maxTemperatureDay2;
    @JsonProperty("enYuksekGun3")
    private Integer maxTemperatureDay3;
    @JsonProperty("enYuksekGun4")
    private Integer maxTemperatureDay4;
    @JsonProperty("enYuksekGun5")
    private Integer maxTemperatureDay5;

    @JsonProperty("enYuksekNemGun1")
    private Integer maxHumidityDay1;
    @JsonProperty("enYuksekNemGun2")
    private Integer maxHumidityDay2;
    @JsonProperty("enYuksekNemGun3")
    private Integer maxHumidityDay3;
    @JsonProperty("enYuksekNemGun4")
    private Integer maxHumidityDay4;
    @JsonProperty("enYuksekNemGun5")
    private Integer maxHumidityDay5;

    @JsonProperty("hadiseGun1")
    private String eventDay1;
    @JsonProperty("hadiseGun2")
    private String eventDay2;
    @JsonProperty("hadiseGun3")
    private String eventDay3;
    @JsonProperty("hadiseGun4")
    private String eventDay4;
    @JsonProperty("hadiseGun5")
    private String eventDay5;

    @JsonProperty("ruzgarHizGun1")
    private Integer windSpeedDay1;
    @JsonProperty("ruzgarHizGun2")
    private Integer windSpeedDay2;
    @JsonProperty("ruzgarHizGun3")
    private Integer windSpeedDay3;
    @JsonProperty("ruzgarHizGun4")
    private Integer windSpeedDay4;
    @JsonProperty("ruzgarHizGun5")
    private Integer windSpeedDay5;

    @JsonProperty("ruzgarYonGun1")
    private Integer windDirectionDay1;
    @JsonProperty("ruzgarYonGun2")
    private Integer windDirectionDay2;
    @JsonProperty("ruzgarYonGun3")
    private Integer windDirectionDay3;
    @JsonProperty("ruzgarYonGun4")
    private Integer windDirectionDay4;
    @JsonProperty("ruzgarYonGun5")
    private Integer windDirectionDay5;

    @JsonProperty("tarihGun1")
    private LocalDateTime dateDay1;
    @JsonProperty("tarihGun2")
    private LocalDateTime dateDay2;
    @JsonProperty("tarihGun3")
    private LocalDateTime dateDay3;
    @JsonProperty("tarihGun4")
    private LocalDateTime dateDay4;
    @JsonProperty("tarihGun5")
    private LocalDateTime dateDay5;
}
