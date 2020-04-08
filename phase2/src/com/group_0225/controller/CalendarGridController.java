package com.group_0225.controller;

import com.group_0225.*;

import java.util.ArrayList;
import java.util.List;

public class CalendarGridController extends CalendarController{

    Timing displayTime;

    public CalendarGridController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);

        displayTime = new Timing(localTime.getStart());
    }

    public void displayGrid() {
        displayGrid(data.getCurrUser());
    }

    public void displayGrid(User user){
        List<String> outputs = new ArrayList<>();

        outputs.add("Display");
        outputs.addAll(displayTime.getInfo());
        outputs.add("Current");
        outputs.addAll(localTime.getInfo());

        presenter.displayPanel(new PanelInfo("CalendarPanel", outputs));
    }

    public void alterMonth(int alter) {
        System.err.println("NEED MANAGERS : " + displayTime + " : " + localTime);
        displayTime.setStart(displayTime.getStart().plusMonths(alter));
        displayGrid(null);
    }
}
