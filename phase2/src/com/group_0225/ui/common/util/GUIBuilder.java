package com.group_0225.ui.common.util;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.core.*;
import com.group_0225.ui.core.Alert.*;

import java.util.Hashtable;
import java.util.Map;

/**
 * This class contains all the CalendarLayoutPanel instances for different features of the calendar
 */
public class GUIBuilder {
    /**
     * Initializes all the panels required for functionality
     * @param controllerContainer a ControllerContainer instance
     * @return a Map of CalendarLayoutPanel names to CalendarLayoutPanel instances
     */
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
        panels.put("DateThresholdPanel", new DateThresholdPanel(controllerContainer));
        panels.put("MemoListPanel", new MemoListPanel(controllerContainer));
        panels.put("AlertListPanel", new AlertListPanel(controllerContainer));
        panels.put("ViewSNameChoicePanel", new ViewSNameChoicePanel(controllerContainer));
        panels.put("NoSeriesPanel", new NoSeriesPanel(controllerContainer));

        panels.put("ViewByTagChoicePanel", new ViewByTagChoicePanel(controllerContainer));
        panels.put("NoTagsPanel", new NoTagsPanel(controllerContainer));
        panels.put("EditAlertButtonsPanel", new EditAlertButtonsPanel(controllerContainer));
        panels.put("AlertChangeTimePanel", new AlertChangeTimePanel(controllerContainer));
        panels.put("AlertChangeMessagePanel", new AlertChangeMessagePanel(controllerContainer));
        panels.put("ResponsePanel", new ResponsePanel(controllerContainer));
        panels.put("ViewByENameChoicePanel", new ViewByENameChoicePanel(controllerContainer));
        panels.put("AlertChangeFrequencyPanel", new AlertChangeFrequencyPanel(controllerContainer));
        panels.put("MemoOptionsPanel", new MemoOptionsPanel(controllerContainer));
        panels.put("ChangeMemoPanel", new ChangeMemoPanel(controllerContainer));
        panels.put("NotificationListPanel", new NotificationListPanel(controllerContainer));
        panels.put("NotificationPanel", new NotificationPanel(controllerContainer));
        return panels;
    }
}
