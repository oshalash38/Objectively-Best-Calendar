package com.group_0225.controller;

import com.group_0225.Main;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.entities.User;
import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.util.ArrayList;
import java.util.List;

public class CalendarGridController extends CalendarController{

    Timing displayTime;

    public CalendarGridController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);

        displayTime = new Timing(data.getLocalTime().getStart());
    }

    public void displayGrid() {
        displayGrid(data.getCurrUser());
    }

    public void displayGrid(User user){
        List<String> outputs = new ArrayList<>();

        outputs.add("Display");
        outputs.addAll(displayTime.getInfo());
        outputs.add("Current");
        outputs.addAll(data.getLocalTime().getInfo());

        presenter.displayPanel(new PanelInfo("CalendarPanel", outputs));
    }

    public void alterMonth(int alter) {
        System.err.println("NEED MANAGERS : " + displayTime + " : " + data.getLocalTime());
        displayTime.setStart(displayTime.getStart().plusMonths(alter));
        displayGrid(null);
    }
}
