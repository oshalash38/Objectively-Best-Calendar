package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import java.awt.*;
import java.util.List;

/**
 * @author Peter
 */
public class CreateSeriesFromEventsPanel extends CalendarLayoutPanel {
    /*
    Display all events and allow user to select (click?) events to form a new series
    Need:
        -some form of id representation
        -can be a list of ids selected, a string of ids separated by commas (preferred), etc
        -let me know which one you have chosen so i can adjust controller accordingly
     */
    public CreateSeriesFromEventsPanel(ControllerContainer facade){
        super(new GridBagLayout(),facade);
    }
    @Override
    protected void buildPanel(List<String> inputs) {
        SeriesController sc = controllerContainer.getSeriesController();

    }
}
