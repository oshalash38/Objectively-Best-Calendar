package com.group_0225.controller;

import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.entities.CalendarData;

public class ControllerContainer {

    protected UIPresenter presenter;

    private LoginController loginController;
    private SeriesController seriesController;
    private EventController eventsController;
    private CalendarGridController calendarGridController;
    private MessagingController messagingController;

    private CalendarData calendarData;
    private Timing localTime;

    public ControllerContainer(UIPresenter presenter, CalendarData calendarData) {
        this.presenter = presenter;
        this.calendarData = calendarData;
        localTime = new Timing();
        loginController = new LoginController(calendarData, presenter, localTime);
        seriesController = new SeriesController(calendarData, presenter, localTime);
        eventsController = new EventController(calendarData, presenter, localTime);
        calendarGridController = new CalendarGridController(calendarData, presenter, localTime);
        messagingController = new MessagingController(calendarData,presenter, localTime);
    }

    public MessagingController getMessagingController(){return messagingController; }
    public LoginController getLoginController() { return loginController; }
    public SeriesController getSeriesController() { return seriesController; }
    public EventController getEventsController() { return eventsController; }
    public CalendarGridController getCalendarGridController() { return calendarGridController; }

//    public void login(String username, String password) throws IOException, ClassNotFoundException {
//        User temp = loginController.login(username, password, calendarGridController);
//        if(temp != null){
//            currUser = temp;
//        }
//    }
//
//    public void createNewUser(){
//        loginController.createNewUserScreen();
//    }
//
//    public void createNewUser(String userName, String password, String repeatPassword) throws IOException, ClassNotFoundException {
//        loginController.createNewUser(userName, password, repeatPassword);
//    }
//
//    public void startUp(){
//        loginController.startUp();
//    }
//
//    public void pushCreateEvent(){
//        eventsController.pushCreateEvent();
//    }
//    public void viewEvents() { eventsController.viewEvents();}
//
//    public void displayGrid(){
//        calendarGridController.displayGrid(currUser);
//    }
//
//    public void alterCalendarTime(int alter) { calendarGridController.alterMonth(alter); }
//
//    public void run(){
//        loginController.startUp();
//    }
//
//    /**
//     * Shows the user past events, current events, or future events
//     * @param status -1: past events
//     *                0: current events
//     *                1: future events
//     */
//    public void viewEventByStatus(int status){
//        eventsController.viewEventByStatus(status);
//    }
//
//    public void createEvent(List<String> input) {
//        eventsController.createEvent(input);
//    }
}
