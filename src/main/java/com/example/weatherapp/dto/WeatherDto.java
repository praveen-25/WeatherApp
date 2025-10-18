package com.example.weatherapp.dto;

import com.example.weatherapp.model.WeatherResponse;
import lombok.Data;

@Data
public class WeatherDto {

	private String city;
	private double temp;
	private int humidity;
	private String description;

	public WeatherDto(WeatherResponse response) {
		this.city = response.getName();
		this.temp = response.getMain().getTemp();
		this.humidity = response.getMain().getHumidity();
		this.description = response.getWeather()[0].getDescription();
	}

	// Constructor for fallback
	public WeatherDto(String city, double temp, int humidity, String description) {
		this.city = city;
		this.temp = temp;
		this.humidity = humidity;
		this.description = description;
	}

	// Fallback constructor with exception details
	public WeatherDto(Throwable ex) {
		this.city = "Unknown";
		this.temp = 0.0;
		this.humidity = 0;
		this.description = "Weather data not available: " + ex.getMessage();
	}
}
