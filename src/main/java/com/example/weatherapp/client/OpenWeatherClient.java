package com.example.weatherapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.weatherapp.model.WeatherResponse;

@FeignClient(name = "openWeather", url ="${weather.api.url}")
public interface OpenWeatherClient {
	@GetMapping("/weather")
    WeatherResponse getWeather(@RequestParam("q") String city,
                               @RequestParam("appid") String apiKey,
                               @RequestParam("units") String units);

}
