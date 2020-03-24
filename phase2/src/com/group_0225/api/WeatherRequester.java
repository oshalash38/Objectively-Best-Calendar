package com.group_0225.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WeatherRequester {

    private final String API_URL = "https://api.openweathermap.org/data/2.5/forecast?q=Toronto&appid=";
    private final String API_KEY = "f9a2d893975845cbdf6f9b9d44ff1453";

    public JsonObject forecastInfo;

    public WeatherRequester() throws IOException {
        requestFiveDayForecast();
    }

    public void requestFiveDayForecast() throws IOException{
        String result = "";
        InputStream is  = new URL(API_URL + API_KEY).openStream();

        BufferedReader buff = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        int rawResult = buff.read();
        while (rawResult != -1) {
            sb.append((char) rawResult);
            rawResult = buff.read();
        }

        result = sb.toString();

        is.close();

        JsonObject weatherInfo = new Gson().fromJson(result, JsonObject.class);

        forecastInfo = weatherInfo;
    }

    public List<Double> getForecastHighs() {
        List<Double> forecasts = new ArrayList<>();

        if(forecastInfo == null)
            return forecasts;

        JsonArray rawList = forecastInfo.get("list").getAsJsonArray();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Canada/Eastern"));

        int currMaxDay = -1;
        double maxTemp = -1;
        for (JsonElement rawElement : rawList) {
            JsonObject forecastJson = rawElement.getAsJsonObject();

            Date forecastDate = new Date(forecastJson.get("dt").getAsLong() * 1000);
            cal.setTime(forecastDate);

            JsonObject weatherInfo = forecastJson.get("main").getAsJsonObject();
            double currTemp = weatherInfo.get("temp").getAsDouble();
            double currDay = cal.get(Calendar.DAY_OF_MONTH);

            if(currMaxDay != currDay) {
                if (currMaxDay != -1) {
                    forecasts.add(kelvinToCelsius(maxTemp));
                    maxTemp = -1;
                }

                currMaxDay = cal.get(Calendar.DAY_OF_MONTH);
            }

            maxTemp = Math.max(maxTemp, currTemp);
        }
        forecasts.add(kelvinToCelsius(maxTemp));

        return forecasts;
    }

    private double kelvinToCelsius(double kelvin) { return kelvin - 273.15; }
}
