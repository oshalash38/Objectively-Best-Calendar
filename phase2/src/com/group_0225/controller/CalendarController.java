package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.manager.CalendarData;

public class CalendarController {

    protected CalendarData data;
    protected UIPresenter presenter;

    public CalendarController(CalendarData data, UIPresenter presenter) {
        this.data = data;
        this.presenter = presenter;
    }
}
