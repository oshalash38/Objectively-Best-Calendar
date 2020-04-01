package com.group_0225;

import com.group_0225.Alert;
import com.group_0225.Event;
import com.group_0225.User;

import java.util.HashMap;
import java.util.Map;

public class CalendarData {

    //contains mappings of ids to events (across all users and calendars)
    private Map<Integer, Event> events = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public Map<String, User> getUsers(){
        return users;
    }

    public User getUser(String username){
        if(users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

    public void addUser(String username, User user){
        users.put(username, user);
    }

    public void setEvents(Map<Integer, Event> events){
        this.events = events;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
