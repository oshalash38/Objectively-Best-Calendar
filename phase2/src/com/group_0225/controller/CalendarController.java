package com.group_0225.controller;

import com.group_0225.*;

public abstract class CalendarController {
    //TODO RENAME THIS CLASS TO CONTROLLER ONCE WE DELETE THE OLD STUFF
    protected CalendarData data;
    protected UIPresenter presenter;
    protected String currCalendar;
    protected Timing localTime;
    protected EventManager eventManager;
    protected User currUser;
    protected TimingFactory timingFactory;

    public CalendarController(CalendarData data, UIPresenter presenter, Timing localTime) {
        this.data = data;
        this.presenter = presenter;
        this.localTime = localTime;
        eventManager = new EventManager();
        this.timingFactory = new TimingFactory();
    }

    public void setCurrCalendar(String calendar){
        currCalendar = calendar;
    }


    public String getCurrCalendar() {
        return currCalendar;
    }
}
