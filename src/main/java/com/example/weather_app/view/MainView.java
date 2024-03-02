package com.example.weather_app.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {

        TextField cityTextField = new TextField("Enter City Name");
        Button WeatherButton = new Button("Get Weather", e -> navigateToWeatherView(cityTextField.getValue()));
        Button AreaButton = new Button("Get Area", e -> navigateToAreaView(cityTextField.getValue()));

        add(cityTextField, WeatherButton, AreaButton);
    }
    private void navigateToWeatherView(String il) {
        getUI().ifPresent(ui -> ui.navigate("weather/" + il));
    }

    private void navigateToAreaView(String il) {
        getUI().ifPresent(ui -> ui.navigate("area/" + il));
    }
}
