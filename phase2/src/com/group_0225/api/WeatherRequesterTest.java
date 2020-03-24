package com.group_0225.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

public class WeatherRequesterTest {

    public static void main(String[] args) {

        try {
            WeatherRequester requester = new WeatherRequester();
            List<Double> forecast = requester.getForecastHighs();
            for(double high : forecast) {
                System.err.println(high);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
