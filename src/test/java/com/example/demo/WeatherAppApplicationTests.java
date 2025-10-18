package com.example.demo;

import com.example.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import com.example.weatherapp.client.OpenWeatherClient;
import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WeatherAppApplicationTests {
	private OpenWeatherClient openWeatherClient;
	private WeatherService weatherService;
	@BeforeEach
		void setup() {
			openWeatherClient = Mockito.mock(OpenWeatherClient.class);
			weatherService = new WeatherService(openWeatherClient, "dummy-key");
		}

		@Test
		void testGetWeather_returnsWeatherDto() {
			WeatherResponse response = new WeatherResponse();
			response.setName("London");
			WeatherResponse.Main main = new WeatherResponse.Main();
			main.setTemp(20.5);
			main.setHumidity(60);
			response.setMain(main);
			WeatherResponse.Weather weather = new WeatherResponse.Weather();
			weather.setDescription("Clear sky");
			response.setWeather(new WeatherResponse.Weather[]{weather});

			Mockito.when(openWeatherClient.getWeather("London", "dummy-key", "metric"))
					.thenReturn(response);

			WeatherDto dto = weatherService.getWeather("London");

			assertNotNull(dto);
			assertEquals("London", dto.getCity());
			assertEquals(20.5, dto.getTemp());
			assertEquals(60, dto.getHumidity());
			assertEquals("Clear sky", dto.getDescription());
		}
	}

