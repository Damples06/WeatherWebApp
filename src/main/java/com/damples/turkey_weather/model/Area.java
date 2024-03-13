package com.damples.turkey_weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cities")
public class Area {
    @Id
    @JsonProperty("merkezId")
    private int areaId;

    @JsonProperty("il")
    private String provinceName;
    @JsonProperty("ilce")
    private String districtName;
    @JsonProperty("aciklama")
    private String description;
    @JsonProperty("yukseklik")
    private int altitude;
    @JsonProperty("boylam")
    private double longitude;
    @JsonProperty("enlem")
    private double latitude;
    @JsonProperty("ilPlaka")
    private int plateCode;
    @JsonProperty("gunlukTahminIstNo")
    private int dailyId;
    @JsonProperty("saatlikTahminIstNo")
    private int hourlyId;
    @JsonProperty("sondurumIstNo")
    private int forecastId;
}