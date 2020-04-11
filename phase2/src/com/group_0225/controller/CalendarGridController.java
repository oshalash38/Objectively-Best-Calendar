package com.group_0225.controller;

import com.group_0225.entities.*;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarGridController extends CalendarController{

    Timing displayTime;
    EventManager eventManager;
    TimingFactory timingFactory;

    public CalendarGridController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);

        displayTime = new Timing(data.getLocalTime().getStart());
        eventManager = new EventManager();
        timingFactory = new TimingFactory();
    }

    public void displayGrid() {
        displayGrid(data.getCurrUser());
    }

    public void displayGrid(User user){
        List<String> outputs = new ArrayList<>();

        outputs.add("Display");
        outputs.addAll(displayTime.getInfo());
        outputs.add("Current");
        outputs.addAll(data.getLocalTime().getInfo());
        outputs.add("Events");
        outputs.addAll(getNumEventsPerDayOfMonth());
        outputs.add("Alerts");
        outputs.addAll(getNumAlertsPerDayOfMonth());

        presenter.updateUI(new UIUpdateInfo("panel", outputs, "CalendarPanel"));
    }

    public void alterMonth(int alter) {
        System.err.println("NEED MANAGERS : " + displayTime + " : " + data.getLocalTime());
        displayTime.setStart(displayTime.getStart().plusMonths(alter));
        displayGrid(null);
    }

    public List<String> getNumEventsPerDayOfMonth() {

        LocalDateTime displayStart = displayTime.getStart();
        LocalDateTime startTime = LocalDateTime.of(displayStart.getYear(), displayStart.getMonth(), 1, 0, 0);
        LocalDateTime endTime = startTime.plusMonths(1);

        Timing threshold = new Timing(startTime, endTime);

        return eventManager.getNumEventsPerDay(data, threshold);
    }

    public List<String> getNumAlertsPerDayOfMonth() {
        LocalDateTime displayStart = displayTime.getStart();
        LocalDateTime startTime = LocalDateTime.of(displayStart.getYear(), displayStart.getMonth(), 1, 0, 0);
        LocalDateTime endTime = startTime.plusMonths(1);

        Timing threshold = new Timing(startTime, endTime);

        return eventManager.getNumAlertsPerDay(data, threshold);
    }
}
