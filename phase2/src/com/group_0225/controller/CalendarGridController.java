package com.group_0225.controller;

import com.group_0225.*;

import java.util.ArrayList;
import java.util.List;

public class CalendarGridController extends CalendarController{

    public CalendarGridController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
        //int month =

        //TODO THIS PART IS TEMP UNTIL MANAGERS WORK
    }

    public void displayGrid(User user){
        List<String> outputs = new ArrayList<String>();

        outputs.addAll(localTime.getInfo());

        presenter.displayPanel(new PanelInfo("CalendarPanel", outputs));
    }

    public void alterMonth(int alter) {
        System.err.println("NEED MANAGERS" + localTime);
        localTime.setStart(localTime.getStart().plusMonths(alter));
        displayGrid(null);
    }
}
