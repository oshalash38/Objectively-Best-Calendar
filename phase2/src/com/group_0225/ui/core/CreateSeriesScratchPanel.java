package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import java.awt.*;
import java.util.List;

public class CreateSeriesScratchPanel extends CalendarLayoutPanel {
    /*Need:
        series name
        an integer from 1-5 inclusive representing the frequency of each event in the series
            1: Hourly
            2: Daily
            3: Weekly
            4: Monthly
            5: Yearly
        the start time (idk if u can do this, but perhaps this could be its separate panel to save space; it was a separate view in phase1)
            Enter year:
            Enter month no: (1-12)
            Enter day of month: (1- 31)
            Enter hour of day (24 hr clock)
            Enter minute of hour (0-59)
        the duration of each event
            Enter number of days (0-6)
            Enter number of hours (24 hour clock)
            Enter number of minutes (0-59)
        the number of events in the series (min. 2)
     */
    public CreateSeriesScratchPanel(ControllerContainer facade){
        super(new GridBagLayout(),facade);
    }
    @Override
    protected void buildPanel(List<String> inputs) {

    }
}
