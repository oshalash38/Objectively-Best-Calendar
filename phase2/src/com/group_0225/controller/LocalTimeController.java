package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.UIPresenter;

public class LocalTimeController extends CalendarController{

    public LocalTimeController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
    }
}
