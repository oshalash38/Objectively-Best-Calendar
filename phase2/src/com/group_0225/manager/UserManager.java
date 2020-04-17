package com.group_0225.manager;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;

import java.util.List;

/**
 * This class receives parsed input from the controllers and manipulates entities to perform user-related functionality
 */
public class UserManager {
    /**
     * Create a user object
     * @param username the username for the new user
     * @param password the password for the new user
     * @param calendarData a CalendarData instance
     * @return the new User
     */
    public User createUser(String username, String password, CalendarData calendarData){
        User temp = new User(username, password);
        calendarData.addUser(username, temp);
        temp.addCalendar("default");
        return temp;
    }

    /**
     * Get all calendar names for this user
     * @param user the user in question
     * @return a list of all calendar names corresponding to user
     */
    public List<String> getCalendarNames(User user){
        return user.getCalendars();
    }

    /**
     * Returns true iff the user has a calendar with the given calendar name
     * @param user the user in question
     * @param calendar the calendar name in question
     * @return true iff the user has a calendar with name calendar
     */
    public boolean containsCalendar(User user, String calendar){
        for(String str: user.getCalendars()){
            if(str.equals(calendar)){
                return true;
            }
        }
        return false;
    }

    /**
     * Add a new calendar for this user
     * @param user the user receiving the new calendar
     * @param calendar the new calendar's name
     * @return true iff the calendar's name is unique
     */
    public boolean addCalendar(User user, String calendar){
        if(containsCalendar(user, calendar)){
            return false;
        }
        user.addCalendar(calendar);
        return true;
    }

    /**
     * Gets all the ids of the events belonging to the given user's given calendar
     * @param user the given user
     * @param calendarName the given calendarName
     * @return a list of Integers of ids of all the events belonging to this calendar
     */
    public List<Integer> getIDs(User user, String calendarName){
        return user.getMap().get(calendarName);
    }

    /**
     * Returns true iff the given user has the same password as <password></password>
     * @param user the given user
     * @param password the password attempt
     * @return true iff the correct password was entered
     */
    public boolean validatePassword(User user, String password){
        return user.validatePassword(password);
    }



}
