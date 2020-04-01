package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.User;
import com.group_0225.CalendarData;

import java.io.IOException;

public class ControllerFacade {

    protected UIPresenter presenter;

    private LoginController loginController;
    private SeriesController seriesController;
    private EventsControllerTemp eventsController;
    private CalendarData calendarData;
    private CalendarGridController calendarGridController;
    private User currUser;

    public ControllerFacade(UIPresenter presenter, CalendarData calendarData) {
        this.presenter = presenter;
        this.calendarData = calendarData;
        loginController = new LoginController(calendarData, presenter);
        seriesController = new SeriesController(calendarData, presenter);
        eventsController = new EventsControllerTemp(calendarData, presenter, currUser);
        calendarGridController = new CalendarGridController(calendarData, presenter);
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

    public void createEvent(){
        // TODO: Needs to be implemented once we implement date picker.
        eventsController.createEvent();
    }

    public void mainMenu(){
        presenter.createCalendarPanel();
    }
}
