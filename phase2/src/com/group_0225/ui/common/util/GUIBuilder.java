package com.group_0225.ui.common.util;

import com.group_0225.entities.CalendarData;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.*;


import java.util.Hashtable;
import java.util.Map;

public class GUIBuilder {

    public Map<String, CalendarLayoutPanel> buildPanels(UIPresenter presenter, CalendarData calendarData, ControllerContainer controllerContainer) {
        Map<String, CalendarLayoutPanel> panels = new Hashtable<>();

        //IMPORTANT
        //THE KEYS TO THE MAP SHOULD BE THE SAME AS THE PANEL THAT THEY MAP TO

        panels.put("StartupPanel", new StartupPanel(controllerContainer));
        panels.put("CreateUserPanel", new CreateUserPanel(controllerContainer));
        panels.put("CreateSeriesScratchPanel", new CreateSeriesScratchPanel(controllerContainer));
        panels.put("CreateSeriesFromEventsPanel", new CreateSeriesFromEventsPanel(controllerContainer));
        panels.put("CalendarPanel", new CalendarPanel(controllerContainer));
        panels.put("CreateEventPanel", new CreateEventPanel(controllerContainer));
        panels.put("EventListPanel", new EventListPanel(controllerContainer));
        panels.put("EventPanel", new EventPanel(controllerContainer));
        return panels;
    }
}
