package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.entities.TimingFactory;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.WeatherManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls high-level logic for the calendar display in the "home screen" of the calendar
 */
public class CalendarGridController extends CalendarController{

    Timing displayTime;
    EventManager eventManager;
    TimingFactory timingFactory;

    WeatherManager weatherManager;

    /**
     * Constructs a CalendarGridController instance
     * @param data a CalendarData instance
     * @param presenter a UIPresenter instance
     */
    public CalendarGridController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);

        displayTime = new Timing(data.getLocalTime().getStart());
        eventManager = new EventManager();
        timingFactory = new TimingFactory();

        weatherManager = new WeatherManager();
        weatherManager.setForecastHighs();
    }

    /**
     * Displays the calendar grid
     */
    public void displayGrid(){
        List<String> outputs = new ArrayList<>();

        outputs.add("Display");
        outputs.addAll(displayTime.getInfo());
        outputs.add("Current");
        outputs.addAll(data.getLocalTime().getInfo());
        outputs.add("Events");
        outputs.addAll(getNumEventsPerDayOfMonth());
        outputs.add("Weather");
        outputs.addAll(weatherManager.getForecastHighs());

        presenter.updateUI(new UIUpdateInfo("panel", outputs, "CalendarPanel"));
    }

    /**
     * Changes the month on the calendar grid
     * @param alter the indicator of the new month in the calendar grid
     */
    public void alterMonth(int alter) {
        displayTime.setStart(displayTime.getStart().plusMonths(alter));
        displayGrid();
    }

    /**
     * Gets the temperature for a particular day
     * @param formattedTemp temperatures formatted as Strings
     * @param day the day in question
     * @param month the month the day belongs to
     * @param year the year the month belongs to
     * @return the correct temperature for the day
     */
    public String getTempForDay(List<String> formattedTemp, String day, String month, String year) {
        String temp = "NONE";

        for(String raw : formattedTemp) {
            String[] rawInfo = raw.split(",");

            String tempDay = rawInfo[0];
            String tempMonth = rawInfo[1];
            String tempYear = rawInfo[2];

            if(tempDay.equals(day) && tempMonth.equals(month) && tempYear.equals(year)) {
                return rawInfo[3];
            }
        }

        return temp;
    }

    /**
     * Get number of events per day of month
     * @return number of events per day of month
     */
    public List<String> getNumEventsPerDayOfMonth() {

        LocalDateTime displayStart = displayTime.getStart();
        LocalDateTime startTime = LocalDateTime.of(displayStart.getYear(), displayStart.getMonth(), 1, 0, 0);
        LocalDateTime endTime = startTime.plusMonths(1);

        Timing threshold = new Timing(startTime, endTime);

        return eventManager.getNumEventsPerDay(data, threshold);
    }

    /**
     * Update the displayed time
     */
    public void updateDisplayTime(){
        displayTime = new Timing(data.getLocalTime().getStart());
    }
}
