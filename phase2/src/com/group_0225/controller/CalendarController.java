package com.group_0225.controller;

import com.group_0225.*;

public abstract class CalendarController {
    //TODO RENAME THIS CLASS TO CONTROLLER ONCE WE DELETE THE OLD STUFF
    protected CalendarData data;
    protected UIPresenter presenter;
    protected Timing localTime;
    protected TimingFactory timingFactory;

    public CalendarController(CalendarData data, UIPresenter presenter, Timing localTime) {
        this.data = data;
        this.presenter = presenter;
        this.localTime = localTime;
        this.timingFactory = new TimingFactory();
    }
}
