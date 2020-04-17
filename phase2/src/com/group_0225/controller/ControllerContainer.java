package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.util.UIPresenter;

/**
 * This class stores all the Controllers used in the program and provides a required controller upon request
 */
public class ControllerContainer {

    protected UIPresenter presenter;

    private LoginController loginController;
    private SeriesController seriesController;
    private EventController eventsController;
    private CalendarGridController calendarGridController;
    private MessagingController messagingController;
    private LocalTimeController localTimeController;
    private UsersCalendarController usersCalendarController;
    private AlertController alertController;
    private MemoController memoController;


    private CalendarData calendarData;

    /**
     * Creates a new ControllerCreator with the required controllers
     * @param presenter the presenter required to change the UI
     * @param calendarData All entities required for runtime
     */
    public ControllerContainer(UIPresenter presenter, CalendarData calendarData) {
        this.presenter = presenter;
        this.calendarData = calendarData;
        loginController = new LoginController(calendarData, presenter);
        seriesController = new SeriesController(calendarData, presenter);
        eventsController = new EventController(calendarData, presenter);
        calendarGridController = new CalendarGridController(calendarData, presenter);
        messagingController = new MessagingController(calendarData,presenter);
        localTimeController = new LocalTimeController(calendarData,presenter);
        usersCalendarController = new UsersCalendarController(calendarData,presenter);
        alertController = new AlertController(calendarData, presenter);
        memoController = new MemoController(calendarData, presenter);
    }

    /**
     * Getter method for UsersCalendarController
     * @return the UsersCalendarController
     */
    public UsersCalendarController getUsersCalendarController(){return usersCalendarController; }

    /**
     * Getter method for MessagingController
     * @return the MessagingController
     */
    public MessagingController getMessagingController(){return messagingController; }

    /**
     * Getter method for LoginController
     * @return the LoginController
     */
    public LoginController getLoginController() { return loginController; }

    /**
     * Getter method for SeriesController
     * @return the SeriesController
     */
    public SeriesController getSeriesController() { return seriesController; }

    /**
     * Getter method for EventController
     * @return the EventController
     */
    public EventController getEventsController() { return eventsController; }

    /**
     * Getter method for CalendarGridController
     * @return the CalendarGridController
     */
    public CalendarGridController getCalendarGridController() { return calendarGridController; }

    /**
     * Getter method for LocalTimeController
     * @return the LocalTimeController
     */
    public LocalTimeController getLocalTimeController(){return localTimeController; }

    /**
     * Getter method for AlertController
     * @return the AlertController being used
     */
    public AlertController getAlertController(){return alertController;}

    /**
     * Getter method for MemoController
     * @return the MemoController being used
     */
    public MemoController getMemoController(){return memoController;}
}
