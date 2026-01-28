package com.example.weatherapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import com.example.weatherapp.client.OpenWeatherClient;
import com.example.weatherapp.dto.WeatherDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final OpenWeatherClient client;
    private final String apiKey;

    public WeatherService(OpenWeatherClient client,
                          @Value("${weather.api.key}") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    @Cacheable(value = "weatherCache", key = "#city")
    @CircuitBreaker(name = "weatherService", fallbackMethod = "fallbackWeather")
    public WeatherDto getWeather(String city) {
        return new WeatherDto(client.getWeather(city, apiKey, "metric"));
    }

    public WeatherDto fallbackWeather(String city, Throwable ex) {
        return new WeatherDto(ex);
    }
}
