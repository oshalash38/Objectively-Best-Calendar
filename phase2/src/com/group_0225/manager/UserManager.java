package com.group_0225.manager;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;

import java.util.List;

public class UserManager {
    public User createUser(String username, String password, CalendarData calendarData){
        User temp = new User(username, password);
        calendarData.addUser(username, temp);
        temp.addCalendar("default");
        return temp;
    }

    public List<String> getCalendarNames(User user){
        return user.getCalendars();
    }

    public boolean containsCalendar(User user, String calendar){
        for(String str: user.getCalendars()){
            if(str.equals(calendar)){
                return true;
            }
        }
        return false;
    }

    public boolean addCalendar(User user, String calendar){
        if(containsCalendar(user, calendar)){
            return false;
        }
        user.addCalendar(calendar);
        return true;
    }

    public List<Integer> getIDs(User user, String calendarName){
        return user.getMap().get(calendarName);
    }
    public boolean validatePassword(User user, String password){
        return user.validatePassword(password);
    }



}
