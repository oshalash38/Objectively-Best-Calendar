package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.manager.CalendarData;

public class ControllerFacade {

    protected UIPresenter presenter;

    private LoginController login;

    private CalendarData data;

    public ControllerFacade(UIPresenter presenter) {
        this.presenter = presenter;

        data = new CalendarData();

        login = new LoginController(data, presenter);
    }

    public void login(String username, String password) { login.login(username, password);}
}
