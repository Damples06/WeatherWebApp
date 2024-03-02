package com.example.weather_app.view;

import com.example.weather_app.model.Daily;
import com.example.weather_app.service.DailyService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Route("weather/:city")
public class WeatherView extends VerticalLayout implements BeforeEnterObserver {

    private final DailyService dailyService;

    private TextField cityField;
    private NumberField degreeField;
    private IntegerField humidityField;
    private NumberField windField;
    private DateTimePicker dateTimePicker;

    @Autowired
    public WeatherView(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    @SneakyThrows
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Check if the cityName parameter is available in the URL
        if (event.getRouteParameters().get("city").isPresent()) {
            String il = event.getRouteParameters().get("city").get();
            displayWeather(il);
        } else {
            // Handle the case where cityName is not present in the URL
            event.forwardTo(MainView.class);
        }
    }

    private void displayWeather(String il) throws IOException, InterruptedException {
        // Use the dailyService to get the weather data for the given city
        // and display it in the UI
        Daily weatherData = dailyService.getDailyWeatherData(il);

        // Display the weather data in the UI
        cityField = new TextField("City");
        cityField.setValue(weatherData.getIl());
        cityField.setReadOnly(true);
        add(cityField);

        degreeField = new NumberField("Degree");
        degreeField.setValue(weatherData.getSicaklik());
        degreeField.setReadOnly(true);
        add(degreeField);

        humidityField = new IntegerField("Humidity");
        humidityField.setValue(weatherData.getNem());
        humidityField.setReadOnly(true);
        add(humidityField);

        windField = new NumberField("Wind");
        windField.setValue(weatherData.getRuzgarHiz());
        windField.setReadOnly(true);
        add(windField);

        dateTimePicker = new DateTimePicker("Date");
        dateTimePicker.setValue(weatherData.getVeriZamani());
        dateTimePicker.setReadOnly(true);
        add(dateTimePicker);

        Button backButton = new Button("Back", event1 -> getUI().ifPresent(ui -> ui.navigate(MainView.class)));
        add(backButton);

    }
}
