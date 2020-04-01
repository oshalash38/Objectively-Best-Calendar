package com.group_0225.controller;

import com.group_0225.Timing;
import com.group_0225.UIPresenter;
import com.group_0225.CalendarData;

public abstract class CalendarController {
    //TODO RENAME THIS CLASS TO CONTROLLER ONCE WE DELETE THE OLD STUFF
    protected CalendarData data;
    protected UIPresenter presenter;
    protected String currCalendar;
    protected Timing localTime;

    public CalendarController(CalendarData data, UIPresenter presenter, Timing localTime) {
        this.data = data;
        this.presenter = presenter;
        this.localTime = localTime;
    }

    public void setCurrCalendar(String calendar){
        currCalendar = calendar;
    }


    public String getCurrCalendar() {
        return currCalendar;
    }
}
