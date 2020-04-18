package com.group_0225.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherRequester {

    private final String API_URL = "https://api.openweathermap.org/data/2.5/forecast?q=Toronto&appid=";
    private final String API_KEY = "f9a2d893975845cbdf6f9b9d44ff1453";

    public JsonObject forecast;

    public void requestFiveDayForecast(){

        try {
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

            forecast = weatherInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public JsonObject getForecast() { return forecast; }


}
