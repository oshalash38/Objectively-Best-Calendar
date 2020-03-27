package com.group_0225.controller;

import com.group_0225.UIPresenter;
import com.group_0225.manager.CalendarData;

public class LoginController extends CalendarController{
    public LoginController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void login(String username, String password) {
        System.err.println(username + " : " + password);


    }
}
