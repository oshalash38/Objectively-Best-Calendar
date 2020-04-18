package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles the high-level logic related to what the user sees on startup
 */
public class LoginController extends CalendarController{
    UserManager userManager;


    /**
     * Creates  an instance of loginController
     * @param data All entities required for runtime
     * @param presenter sends commands to change UI
     */
    public LoginController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        userManager = new UserManager();
    }

    /**
     * Logs in the user with the given username and password and changes the UI to the calendar grid
     * @param username the username of the user trying to login
     * @param password the password of the user trying to login
     * @param calendarGridController changes the view if the user successfully logged in
     * @param usersCalendarController changes the calendars available to the logged in user
     */
    public void login(String username, String password,
                      CalendarGridController calendarGridController, UsersCalendarController usersCalendarController){

        User user = data.getUser(username);
        if(username.length() == 0 || password.length() == 0){
            presenter.updateUI(new UIUpdateInfo("panel", Arrays.asList(username, "Please enter all fields"), "StartupPanel"));
        }
        else if(user == null || !userManager.validatePassword(user, password)){
            presenter.updateUI(new UIUpdateInfo("panel", Arrays.asList(username, "Sorry, username and password did not match."), "StartupPanel"));
        } else {
            data.setCurrUser(user);
            data.setCurrCalendar("default");
            usersCalendarController.updateCalendars();
            calendarGridController.displayGrid();
            new AlertController(data, presenter).start();
        }
   }

    /**
     * Pushes the screen to create a new User
     */
    public void createNewUserScreen(){
        pushCreateNewUserScreen(Arrays.asList("", ""));
    }


    /**
     * Creates a new user with the given username and password
     * @param username the username of the new user
     * @param password the password of the new user
     * @param repeatPassword the password of the new user repeated
     * @throws IOException
     * @throws ClassNotFoundException
     */
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
        presenter.updateUI(new UIUpdateInfo("panel", args, "CreateUserPanel" ));
    }

    /**
     * Displays the startup panel
     */
    public void startUp(){
        data.setCurrUser(null);
        presenter.updateUI(new UIUpdateInfo("panel", Arrays.asList("", ""), "StartupPanel"));
    }
}
