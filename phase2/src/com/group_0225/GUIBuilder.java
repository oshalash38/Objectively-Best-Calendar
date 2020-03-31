package com.group_0225;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.*;


import java.util.Hashtable;
import java.util.Map;

public class GUIBuilder {

    public Map<String, CalendarLayoutPanel> buildPanels(UIPresenter presenter) {
        ControllerFacade facade = new ControllerFacade(presenter);

        Map<String, CalendarLayoutPanel> panels = new Hashtable<>();
        panels.put("StartupPanel", new StartupPanel(facade));
        panels.put("CreateUserPanel", new CreateUserPanel(facade));
        panels.put("CreateSeriesScratchPanel", new CreateSeriesScratchPanel(facade));
        panels.put("CreateSeriesFromEventsPanel", new CreateSeriesFromEventsPanel(facade));
        panels.put("CalendarPanel", new CalendarPanel(facade));
        //TODO is the above an error or intentional? It didn't compile on my machine
        return panels;
    }
}
