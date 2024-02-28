package com.example.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class Daily {
    @Id
    @JsonProperty("il")
    private String il;

    @JsonProperty("karYukseklik")
    private int karYukseklik;

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
