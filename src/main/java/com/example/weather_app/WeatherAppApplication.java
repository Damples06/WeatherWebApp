package com.example.weather_app;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "mytodo")
public class WeatherAppApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

}