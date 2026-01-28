package com.example.weatherapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.service.WeatherService;

@Controller
public class UiController {
    private final WeatherService weatherService;

    public UiController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ui/weather")
    public String getWeather(@RequestParam String city, Model model) {
        WeatherDto weather = weatherService.getWeather(city);
        model.addAttribute("weather", weather);
        return "index";
    }
}