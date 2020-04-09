package com.group_0225.controller;

import com.group_0225.Main;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.entities.User;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginController extends CalendarController{
    UserManager userManager;

    public LoginController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
        userManager = new UserManager();
    }

    public void login(String username, String password, CalendarGridController calendarGridController){

        User user = data.getUser(username);
        System.err.println(user);
        if(username.length() == 0 || password.length() == 0){
            presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList(username, "Please enter all fields")));
        }
        else if(user == null || !userManager.validatePassword(user, password)){
            presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList(username, "Sorry, username and password did not match.")));
        } else {
            System.out.println("Login successful ");
            data.setCurrUser(user);
            calendarGridController.displayGrid(user);
        }
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
        data.setCurrUser(null);
        presenter.displayPanel(new PanelInfo("StartupPanel", Arrays.asList("", "")));
    }
}
