package com.group_0225;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.CreateSeriesFromEventsPanel;
import com.group_0225.ui.core.CreateSeriesScratchPanel;
import com.group_0225.ui.core.CreateUserPanel;
import com.group_0225.ui.core.StartupPanel;

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
        return panels;
    }
}
