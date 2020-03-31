package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.manager.CalendarData;

import java.io.IOException;

public class ControllerFacade {

    protected UIPresenter presenter;

    private LoginController login;
    private SeriesController series;

    private CalendarData data;

    public ControllerFacade(UIPresenter presenter) {
        this.presenter = presenter;
        data = new CalendarData();
        login = new LoginController(data, presenter);
        series = new SeriesController(data, presenter);
    }

    public void login(String username, String password) throws IOException, ClassNotFoundException { login.login(username, password);}

    public void createNewUser(){
        login.createNewUser();
    }

    public void createNewUser(String userName, String password, String repeatPassword) {
        login.createNewUser(userName, password, repeatPassword);
    }

    public void startUp(){
        login.startUp();
    }
}
