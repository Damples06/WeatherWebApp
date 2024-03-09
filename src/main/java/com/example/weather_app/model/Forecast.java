package com.example.weather_app.model;

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

    // This method is used to regularize the data that comes from the MGM Service.
    public void regularize(){
        if (this.seaTemperature == -9999)
            this.seaTemperature = null;
        if (this.snowHeight == -9999)
            this.snowHeight = null;
        this.windSpeed = (double) Math.round(this.windSpeed * 100) / 100;
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
