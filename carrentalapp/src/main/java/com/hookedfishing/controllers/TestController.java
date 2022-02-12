package com.hookedfishing.controllers;

import com.hookedfishing.payloads.api.response.openweatherapi.Forecast;
import com.hookedfishing.payloads.api.response.weatherapi.CurrentWeather;
import com.hookedfishing.payloads.api.response.weatherapi.WeatherAPI;
import com.hookedfishing.payloads.api.response.weatherapi.WeatherForecast;
import com.hookedfishing.payloads.api.response.weatherapi.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${FishingWeatherApp.app.openWeatherApiKey}")
    private String apiKey;

    @Value("${FishingWeatherApp.app.weatherApiKey}")
    private String apiKey2;

    @GetMapping("/weathertest")
    public String weatherTest() {
        return "WEATHER TEST!";
    }

    @GetMapping("/forecast")
    public ResponseEntity<?> getForecast() {

        String LOCATION = "Cranston";
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + apiKey;

        Forecast forecast = restTemplate.getForObject(uri, Forecast.class);

        return ResponseEntity.ok(forecast);
    }

    @GetMapping("/forecast/{name}")
    public ResponseEntity<?> getWeatherForecast(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + apiKey2 + "&q=" + name + "&aqi=no";

        WeatherAPI response = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(response.getLocation());
    }

    @GetMapping("/forecastv2/{name}")
    public ResponseEntity<?> getForecastV2(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + apiKey2 + "&q=" + name + "&aqi=no";

        WeatherResponse response = restTemplate.getForObject(uri, WeatherResponse.class);

        return ResponseEntity.ok(response.getForecast());
    }

    @GetMapping("/forecastv3/{name}")
    public ResponseEntity<?> getWeatherForecastV3(@PathVariable String name) {

        String uri = "http://api.weatherapi.com/v1/current.json?key=" + apiKey2 + "&q=" + name + "&aqi=no";

        WeatherAPI response = restTemplate.getForObject(uri, WeatherAPI.class);

        return ResponseEntity.ok(response.getCurrent());
    }
}
