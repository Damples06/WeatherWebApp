package com.example.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Locale;

@Entity
@Data
@Table(name = "cities")
public class Area {
    @Id
    @JsonProperty("merkezId")
    private int merkezId;

    @JsonProperty("il")
    private String il;

    @JsonProperty("ilce")
    private String ilce;

    @JsonProperty("aciklama")
    private String aciklama;

    @JsonProperty("boylam")
    private double boylam;

    @JsonProperty("enlem")
    private double enlem;

    @JsonProperty("gunlukTahminIstNo")
    private int gunlukTahminIstNo;

    @JsonProperty("ilPlaka")
    private int ilPlaka;

    @JsonProperty("saatlikTahminIstNo")
    private int saatlikTahminIstNo;

    @JsonProperty("sonDurumIstNo")
    private int sondurumIstNo;

    @JsonProperty("yukseklik")
    private int yukseklik;

    public void convertToLowerCase() {
        if (il != null) {
            this.il = this.il.toLowerCase(new Locale("tr", "TR"));
        }
        if (ilce != null) {
            this.ilce = this.ilce.toLowerCase(new Locale("tr", "TR"));
        }
        if (aciklama != null) {
            this.aciklama = this.aciklama.toLowerCase(new Locale("tr", "TR"));
        }
    }
}