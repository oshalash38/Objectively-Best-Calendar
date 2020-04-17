package com.group_0225.manager;

import com.google.gson.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.group_0225.api.WeatherRequester;

import java.util.*;

/**
 * This class manages weather-related functionality
 */
public class WeatherManager {

    WeatherRequester requester;

    List<String> forecast;

    /**
     * Creates a WeatherManager instance
     */
    public WeatherManager() {
        requester = new WeatherRequester();
        requester.requestFiveDayForecast();
        forecast = new ArrayList<>();
    }

    /**
     * Sets the forecasted highs for the next few days
     */
    public void setForecastHighs() {

        forecast.clear();

        try {
            JsonObject forecastInfo = requester.getForecast();

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
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        forecast.add(formatForecast(cal, kelvinToCelsius(maxTemp)));
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        maxTemp = -1;
                    }

                    currMaxDay = cal.get(Calendar.DAY_OF_MONTH);
                }

                maxTemp = Math.max(maxTemp, currTemp);
            }
        } catch (Exception e) {
            forecast.clear();
            e.printStackTrace();
        }


    }

    private String formatForecast(Calendar cal, double forecast) {

        String formattedForecast = "";
        formattedForecast += cal.get(Calendar.DAY_OF_MONTH) + ",";
        formattedForecast += (cal.get(Calendar.MONTH) + 1) + ",";
        formattedForecast += cal.get(Calendar.YEAR) + ",";
        formattedForecast += ((int) forecast) + "";


        return formattedForecast;
    }

    private double kelvinToCelsius(double kelvin) { return kelvin - 273.15; }

    /**
     * Getter for the forecasted highs for the next few days
     * @return the temperatures as a list of strings
     */
    public List<String> getForecastHighs() { return forecast; }
}
