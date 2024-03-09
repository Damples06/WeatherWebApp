package com.example.weather_app.model;

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

    // This method is used to regularize the data that comes from the MGM Service.
    public void regularize() {
        this.date = this.date.plusHours(3);
        switch (this.event) {
            case "A":
                this.event = "Açık";
                break;
            case "AB":
                this.event = "Az Bulutlu";
                break;
            case "PB":
                this.event = "Parçalı Bulutlu";
                break;
            case "CB":
                this.event = "Çok Bulutlu";
                break;
            case "HY":
                this.event = "Hafif Yağmurlu";
                break;
            case "Y":
                this.event = "Yağmurlu";
                break;
            case "KY":
                this.event = "Kuvvetli Yağmurlu";
                break;
            case "KKY":
                this.event = "Karla Karışık Yağmurlu";
                break;
            case "HKY":
                this.event = "Hafif Kar Yağışlı";
                break;
            case "K":
                this.event = "Kar Yağışlı";
                break;
            case "YKY":
                this.event = "Yoğun Kar Yağışlı";
                break;
            case "HSY":
                this.event = "Hafif Sağanak Yağışlı";
                break;
            case "SY":
                this.event = "Sağanak Yağışlı";
                break;
            case "KSY":
                this.event = "Kuvvetli Sağanak Yağışlı";
                break;
            case "MSY":
                this.event = "Mevzi Sağanak Yağışlı";
                break;
            case "DY":
                this.event = "Dolu";
                break;
            case "GSY":
                this.event = "Gökgürültülü Sağanak Yağışlı";
                break;
            case "KGY":
                this.event = "Kuvvetli Gökgürültülü Sağanak Yağışlı";
                break;
            case "SIS":
                this.event = "Sisli";
                break;
            case "PUS":
                this.event = "Puslu";
                break;
            case "DMN":
                this.event = "Dumanlı";
                break;
            case "KF":
                this.event = "Kum veya Toz Taşınımı";
                break;
            case "R":
                this.event = "Rüzgarlı";
                break;
            case "GKR":
                this.event = "Güneyli Kuvvetli Rüzgar";
                break;
            case "KKR":
                this.event = "Kuzeyli Kuvvetli Rüzgar";
                break;
            case "SCK":
                this.event = "Sıcak";
                break;
            case "SGK":
                this.event = "Soğuk";
                break;
            case "HHY":
                this.event = "Yağışlı";
                break;
            default:
                this.event = "Bilinmiyor";
        }
    }
}
