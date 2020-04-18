package com.group_0225.api;

import com.group_0225.manager.WeatherManager;

import java.util.List;

public class WeatherRequesterTest {

    public static void main(String[] args) {
        WeatherManager weather = new WeatherManager();
        weather.setForecastHighs();
        List<String> forecast = weather.getForecastHighs();
    }
}
