package com.group_0225;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.StartupPanel;

import java.util.Hashtable;
import java.util.Map;

public class GUIBuilder {

    public Map<String, CalendarLayoutPanel> buildPanels(UIPresenter presenter) {
        ControllerFacade facade = new ControllerFacade(presenter);

        Map<String, CalendarLayoutPanel> panels = new Hashtable<>();
        panels.put("Startup", new StartupPanel(facade));

        return panels;
    }
}
