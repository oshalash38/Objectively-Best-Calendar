package com.group_0225.manager;

import com.group_0225.Main;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;

public class UserManager {
    public User createUser(String username, String password, CalendarData calendarData){
        User temp = new User(username, password);
        calendarData.addUser(username, temp);
        temp.addCalendar("default");
        return temp;
    }

    public boolean validatePassword(User user, String password){
        return user.validatePassword(password);
    }



}
