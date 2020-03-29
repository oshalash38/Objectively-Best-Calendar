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

    public void createNewUser(){
        presenter.createUserPanel();
    }

    public void createNewUser(String userName, String password, String repeatPassword) {
        if(!password.equals(repeatPassword)){
            presenter.createUserPanel(userName, "Passwords do not match"); //TODO I'm not sure if a controller is allowed to have string messages
        }
        //else if(username already exists){
            // presenter.CreateUserPanel(userName, "Username already exists please try again");
        //}
        else{
            // Create user I don't know how to do this yet.
            // presenter.CreateUserPanel(userName, "User creation successful. Return to main menu to login");
        }
    }

    public void startUp(){
        presenter.startUp();
    }
}
