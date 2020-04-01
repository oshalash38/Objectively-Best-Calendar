package com.group_0225;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.*;


import java.util.Hashtable;
import java.util.Map;

public class GUIBuilder {

    public Map<String, CalendarLayoutPanel> buildPanels(UIPresenter presenter, CalendarData calendarData, ControllerFacade controllerFacade) {
        Map<String, CalendarLayoutPanel> panels = new Hashtable<>();

        panels.put("StartupPanel", new StartupPanel(controllerFacade));
        panels.put("CreateUserPanel", new CreateUserPanel(controllerFacade));
        panels.put("CreateSeriesScratchPanel", new CreateSeriesScratchPanel(controllerFacade));
        panels.put("CreateSeriesFromEventsPanel", new CreateSeriesFromEventsPanel(controllerFacade));
        panels.put("CalendarPanel", new CalendarPanel(controllerFacade));
        panels.put("CreateEventPanel", new CreateEventPanel(controllerFacade));
        return panels;
    }
}
