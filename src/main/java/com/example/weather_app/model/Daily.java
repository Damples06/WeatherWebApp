package com.example.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Daily {
    @Id
    @JsonProperty("il")
    private String il;

    @JsonProperty("nem")
    private int nem;

    @JsonProperty("ruzgarHiz")
    private double ruzgarHiz;

    @JsonProperty("ruzgarYon")
    private int ruzgarYon;

    @JsonProperty("sicaklik")
    private double sicaklik;

    @JsonProperty("veriZamani")
    private LocalDateTime veriZamani;
}
