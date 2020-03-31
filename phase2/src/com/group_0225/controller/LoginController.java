package com.group_0225.controller;

import com.group_0225.DatabaseManager;
import com.group_0225.UIPresenter;
import com.group_0225.User;
import com.group_0225.manager.CalendarData;

import java.io.IOException;

public class LoginController extends CalendarController{
    public LoginController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void login(String username, String password) throws IOException, ClassNotFoundException {
        System.err.println(username + " : " + password);
        DatabaseManager dm = new DatabaseManager("database.ser");
        User user = dm.findUser(username);
        if (user != null){
            presenter.createCalendarPanel();
            currUser = user;
        }
    }

    public void createNewUser(){
        presenter.createUserPanel();
    }

    public void createNewUser(String userName, String password, String repeatPassword) throws IOException, ClassNotFoundException {


        if(userName.length() == 0 || password.length() == 0 || repeatPassword.length() == 0){
            presenter.createUserPanel(userName, "Please enter all fields."); //TODO I'm not sure if a controller is allowed to have string messages
        }
        else if(!password.equals(repeatPassword)){
            presenter.createUserPanel(userName, "Passwords do not match. Please try again."); //TODO I'm not sure if a controller is allowed to have string messages
        }
        //else if(username already exists){
            // presenter.CreateUserPanel(userName, "Username already exists please try again");
        //}
        else{
            User user = new User(userName, password); // TODO: Not sure if controller can access user directly or not.
            DatabaseManager databaseManager = new DatabaseManager("database.ser");
            databaseManager.add(user);
            databaseManager.saveToFile("database.ser");
            presenter.createUserPanel(userName, "User creation successful. Return to main menu to login");
        }
    }

    public void startUp(){
        presenter.startUp();
    }
}
