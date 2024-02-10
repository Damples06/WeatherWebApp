package com.example.weather_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class Area {

    @Id
    private Long plakaId;
    private String merkezId;

    private String provinceName;
    private String districtName;

    private String degree;

    private String windSpeed;
    private String windDirection;

    private String humidity;
}
