package com.group_0225.controller;

import com.group_0225.Timing;
import com.group_0225.UIPresenter;
import com.group_0225.User;
import com.group_0225.CalendarData;

import java.io.IOException;
import java.util.List;

public class ControllerFacade {

    protected UIPresenter presenter;

    private LoginController loginController;
    private SeriesController seriesController;
    private EventController eventsController;
    private CalendarData calendarData;
    private CalendarGridController calendarGridController;
    private Timing localTime = new Timing();
    private User currUser;

    public ControllerFacade(UIPresenter presenter, CalendarData calendarData) {
        this.presenter = presenter;
        this.calendarData = calendarData;
        loginController = new LoginController(calendarData, presenter, localTime);
        seriesController = new SeriesController(calendarData, presenter, localTime);
        eventsController = new EventController(calendarData, presenter, localTime);
        calendarGridController = new CalendarGridController(calendarData, presenter, localTime);
        localTime = new Timing();
    }

    public void login(String username, String password) throws IOException, ClassNotFoundException {
        User temp = loginController.login(username, password, calendarGridController);
        if(temp != null){
            currUser = temp;
        }
    }

    public void createNewUser(){
        loginController.createNewUserScreen();
    }

    public void createNewUser(String userName, String password, String repeatPassword) throws IOException, ClassNotFoundException {
        loginController.createNewUser(userName, password, repeatPassword);
    }

    public void startUp(){
        loginController.startUp();
    }

    public void pushCreateEvent(){
        eventsController.pushCreateEvent();
    }
    public void viewEvents() { eventsController.viewEvents();}

    public void displayGrid(){
        calendarGridController.displayGrid(currUser);
    }

    public void alterCalendarTime(int alter) { calendarGridController.alterMonth(alter); }

    public void run(){
        loginController.startUp();
    }

    /**
     * Shows the user past events, current events, or future events
     * @param status -1: past events
     *                0: current events
     *                1: future events
     */
    public void viewEventByStatus(int status){
        eventsController.viewEventByStatus(status);
    }

    public void createEvent(List<String> input) {
        eventsController.createEvent(input);
    }
}
