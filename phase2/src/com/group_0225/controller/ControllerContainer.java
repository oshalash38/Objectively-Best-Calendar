package com.group_0225.controller;

import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.entities.CalendarData;

import java.time.LocalDateTime;

public class ControllerContainer {

    protected UIPresenter presenter;

    private LoginController loginController;
    private SeriesController seriesController;
    private EventController eventsController;
    private CalendarGridController calendarGridController;
    private MessagingController messagingController;
    private LocalTimeController localTimeController;

    private CalendarData calendarData;

    public ControllerContainer(UIPresenter presenter, CalendarData calendarData) {
        this.presenter = presenter;
        this.calendarData = calendarData;
        loginController = new LoginController(calendarData, presenter);
        seriesController = new SeriesController(calendarData, presenter);
        eventsController = new EventController(calendarData, presenter);
        calendarGridController = new CalendarGridController(calendarData, presenter);
        messagingController = new MessagingController(calendarData,presenter);
        localTimeController = new LocalTimeController(calendarData,presenter);
    }

    public MessagingController getMessagingController(){return messagingController; }
    public LoginController getLoginController() { return loginController; }
    public SeriesController getSeriesController() { return seriesController; }
    public EventController getEventsController() { return eventsController; }
    public CalendarGridController getCalendarGridController() { return calendarGridController; }
}
