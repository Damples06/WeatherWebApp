package com.damples.turkey_weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Hourly {
    @Id
    @JsonProperty("istNo")
    private Integer hourlyId;

    @JsonProperty("sicaklik")
    private Integer temperature;
    @JsonProperty("hissedilenSicaklik")
    private Integer feelsLike;
    @JsonProperty("nem")
    private Integer humidity;
    @JsonProperty("ruzgarHizi")
    private Integer windSpeed;
    @JsonProperty("maksimumRuzgarHizi")
    private Integer maxWindSpeed;
    @JsonProperty("ruzgarYonu")
    private Integer windDirection;
    @JsonProperty("hadise")
    private String event;
    @JsonProperty("tarih")
    private LocalDateTime date;
}
