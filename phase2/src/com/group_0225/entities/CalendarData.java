package com.group_0225.entities;

import java.util.HashMap;
import java.util.Map;

public class CalendarData {

    //contains mappings of ids to events (across all users and calendars)
    private User currUser;
    private String currCalendar;
    private Timing localTime;
    private Map<Integer, Event> events = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public Map<String, User> getUsers(){
        return users;
    }

    public User getUser(String username){
        System.err.println(users.containsKey(username));
        if(users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

    public Timing getLocalTime(){ return localTime;}
    public void setLocalTime(Timing localTime){this.localTime = localTime;}

    public User getCurrUser() { return currUser; }
    public void setCurrUser(User currUser) { this.currUser = currUser; }

    public String getCurrCalendar() { return currCalendar; }
    public void setCurrCalendar(String currCalendar) { this.currCalendar = currCalendar; }

    public void addUser(String username, User user){
        users.put(username, user);
    }

    public void addEvent(int id, Event event){
        events.put(id, event);
    }

    public void setEvents(Map<Integer, Event> events){
        this.events = events;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
