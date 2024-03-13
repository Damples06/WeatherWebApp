package com.damples.turkey_weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Daily {
    @JsonProperty("enDusukSicaklik")
    private Integer minTemperature;
    @JsonProperty("enYuksekSicaklik")
    private Integer maxTemperature;
    @JsonProperty("enDusukNem")
    private Integer minHumidity;
    @JsonProperty("enYuksekNem")
    private Integer maxHumidity;
    @JsonProperty("ruzgarHiz")
    private Integer windSpeed;
    @JsonProperty("ruzgarYon")
    private Integer windDirection;
    @JsonProperty("hadise")
    private String event;
    @JsonProperty("tarih")
    private LocalDate date;
}
