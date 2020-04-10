package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.UIPresenter;

public class LocalTimeController extends CalendarController{

    public LocalTimeController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    //3 methods
    //      back to the present
    //      pushChangeTime
    //      Take in input from the dialog, parse it, change Timing inside CalendarData
}
