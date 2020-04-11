package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.entities.TimingFactory;
import com.group_0225.ui.common.util.UIPresenter;

import java.util.HashMap;
import java.util.Map;

public abstract class CalendarController {
    //TODO RENAME THIS CLASS TO CONTROLLER ONCE WE DELETE THE OLD STUFF
    protected CalendarData data;
    protected UIPresenter presenter;
    protected Map<String, Integer> Months;

    public CalendarController(CalendarData data, UIPresenter presenter) {
        this.data = data;
        this.presenter = presenter;
        this.Months = new HashMap<>();
        Months.put("Jan", 1);
        Months.put("Feb", 2);
        Months.put("Mar", 3);
        Months.put("Apr", 4);
        Months.put("May", 5);
        Months.put("Jun", 6);
        Months.put("Jul", 7);
        Months.put("Aug", 8);
        Months.put("Sep", 9);
        Months.put("Oct", 10);
        Months.put("Nov", 11);
        Months.put("Dec", 12);
    }
}
