package com.group_0225.controller;

import com.group_0225.*;
import com.group_0225.CalendarData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginController extends CalendarController{
    UserManager userManager;

    public LoginController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
        userManager = new UserManager();
    }

    public User login(String username, String password, CalendarGridController calendarGridController) throws IOException, ClassNotFoundException {

        User user = data.getUser(username);
        if(username.length() == 0 || password.length() == 0){
            presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList(username, "Please enter all fields")));
            return null;
        }
        else if(user == null || !userManager.validatePassword(user, password)){
            presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList(username, "Sorry, username and password did not match.")));
            return null;
        }
        System.out.println("Login successful ");
        calendarGridController.displayGrid(user);
        data.setCurrUser(user);
        return user;
    }

    public void createNewUserScreen(){
        pushCreateNewUserScreen(Arrays.asList("", ""));
    }


    public void createNewUser(String username, String password, String repeatPassword) throws IOException, ClassNotFoundException {
        if(username.length() == 0 || password.length() == 0 || repeatPassword.length() == 0){
            pushCreateNewUserScreen( Arrays.asList(username, "Please enter all fields."));//TODO I'm not sure if a controller is allowed to have string messages
        }
        else if(!password.equals(repeatPassword)){
            pushCreateNewUserScreen(Arrays.asList(username, "Passwords do not match. Please try again."));
        }
        else if(data.getUser(username) != null){
            pushCreateNewUserScreen(Arrays.asList(username, "Username already exists please try again."));
        }
        else {
            userManager.createUser(username, password, data);
            pushCreateNewUserScreen(Arrays.asList(username, "User creation successful. Return to main menu to login."));
        }
    }

    private void pushCreateNewUserScreen(List<String> args){
        presenter.displayPanel(new PanelInfo("CreateUserPanel", args));
    }

    public void startUp(){
        presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList("", "")));
    }
}
