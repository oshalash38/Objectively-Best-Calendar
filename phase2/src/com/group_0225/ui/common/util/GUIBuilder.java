package com.group_0225.ui.common.util;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.*;


import java.util.Hashtable;
import java.util.Map;

public class GUIBuilder {

    public Map<String, CalendarLayoutPanel> buildPanels(ControllerContainer controllerContainer) {
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
        panels.put("SeriesChoicePanel", new SeriesChoicePanel(controllerContainer));
        panels.put("SendMessage", new SendMessagePanel(controllerContainer));
        panels.put("CreateCalendarPanel", new CreateCalendarPanel(controllerContainer));
        panels.put("Inbox", new InboxPanel(controllerContainer));
        panels.put("ChangeTimePanel", new ChangeTimePanel(controllerContainer));
        panels.put("AcceptDeclineMessagePanel", new AcceptDeclineMessagePanel(controllerContainer));
        panels.put("CreateAlertPromptPanel", new CreateAlertPromptPanel(controllerContainer));
        panels.put("CreateOneTimeAlertPanel", new CreateOneTimeAlertPanel(controllerContainer));
        panels.put("CreateRepeatingAlertPanel", new CreateRepeatingAlertPanel(controllerContainer));
        panels.put("CreateMemoPanel", new CreateMemoPanel(controllerContainer));

        return panels;
    }
}
