package com.group_0225.controller;

import com.group_0225.CalendarData;
import com.group_0225.PanelInfo;
import com.group_0225.Timing;
import com.group_0225.UIPresenter;

import java.util.Calendar;

public class EventController extends CalendarController {

    public EventController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
    }

    public void pushCreateEvent(){
        presenter.displayPanel(new PanelInfo("CreateEventPanel", null, true));
    }

    /**
     * Shows the user past events, current events, or future events
     * @param status -1: past events
     *                0: current events
     *                1: future events
     */
    public void viewEventByStatus(int status){

    }
}
