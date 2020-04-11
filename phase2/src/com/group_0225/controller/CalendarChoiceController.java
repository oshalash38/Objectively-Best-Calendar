package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.util.UIPresenter;

public class CalendarChoiceController extends CalendarController {

    public CalendarChoiceController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void changeCalendar(String userChoice, CalendarGridController calendarGridController){
        data.setCurrCalendar(userChoice);
        calendarGridController.displayGrid();
    }
}
