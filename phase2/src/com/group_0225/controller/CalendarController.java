package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.User;
import com.group_0225.manager.CalendarData;

public class CalendarController {
    //TODO RENAME THIS CLASS TO CONTROLLER ONCE WE DELETE THE OLD STUFF
    protected CalendarData data;
    protected UIPresenter presenter;
    private String currCalendar;
    protected User currUser;

    public CalendarController(CalendarData data, UIPresenter presenter) {
        this.data = data;
        this.presenter = presenter;
    }

    public void setCurrCalendar(String calendar){
        currCalendar = calendar;
    }


    public String getCurrCalendar() {
        return currCalendar;
    }
}
