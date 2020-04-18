package com.group_0225.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class stores user-irrelevant information for the program
 */
public class CalendarData {

    //contains mappings of ids to events (across all users and calendars)
    private User currUser;
    private String currCalendar;
    private Timing localTime;
    private Map<Integer, Event> events = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, String> memos = new HashMap<>();

    /**
     * Getter method for the map of event ids to events
     * @return a Map
     */
    public Map<Integer, Event> getEvents() {
        return events;
    }

    /**
     * Getter for the map
     * @return a Map
     */
    public Map<String, User> getUsers(){
        return users;
    }

    /**
     * Getter for the map of memo ids to Memo messages
     * @return a map
     */
    public Map<Integer, String> getMemos() {return memos;}

    /**
     * Getter for the user that has this username
     * @param username the given username
     * @return the user that has this usernmame, or null if no user has this username
     */
    public User getUser(String username){
        if(users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

    /**
     * Returns the timing instance associated with localTime
     * @return a Timing instance
     */
    public Timing getLocalTime(){ return localTime;}

    /**
     * Setter for the localTime
     * @param localTime a Timing instance
     */
    public void setLocalTime(Timing localTime){this.localTime = localTime;}

    /**
     * Getter for the current user
     * @return the current user
     */
    public User getCurrUser() { return currUser; }

    /**
     * Setter for the current user
     * @param currUser the new current user
     */
    public void setCurrUser(User currUser) { this.currUser = currUser; }

    /**
     * Getter for the current calendar name
     * @return the current calendar name
     */
    public String getCurrCalendar() { return currCalendar; }

    /**
     * Setter for the current calendar name
     * @param currCalendar the new calendar name
     */
    public void setCurrCalendar(String currCalendar) { this.currCalendar = currCalendar; }

    /**
     * Modifier for the user map
     * @param username the username of the new user
     * @param user the new user instance
     */
    public void addUser(String username, User user){
        users.put(username, user);
    }

    /**
     * Modifier for the event map
     * @param id the new event id
     * @param event the new Event instance
     */
    public void addEvent(int id, Event event){
        events.put(id, event);
        currUser.addIdToCalendars(currCalendar, id);
    }

    /**
     * Modifier for the memo map
     * @param id the new memo id
     * @param memo the new memo message
     */
    public void addMemo(int id, String memo){
        memos.put(id, memo);
    }

    /**
     * Setter for the events map
     * @param events the new map of event ids to events
     */
    public void setEvents(Map<Integer, Event> events){
        this.events = events;
    }

    /**
     * Setter for the users map
     * @param users the new map of usernames to users
     */
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    /**
     * Getter of the current user's events
     * @return the current user's events as a list of events
     */
    public List<Event> getCurrUserEvents(){
        List<Event> result = new ArrayList<>();
        List<Integer> ids =  currUser.getEvents(currCalendar);
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (ids.contains(entry.getKey())){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    /**
     * setter for the memo map
     * @param memos the new memo map
     */
    public void setMemos(Map<Integer, String> memos){
        this.memos = memos;
    }

    /**
     * Return an event based on the given event name
     * @param eventName the user's selected event name
     * @return the event corresponding to the given event name, or null if no event corresponds to the name
     */
    public Event getEventByName(String eventName){
        List<Event> currUserEvents = getCurrUserEvents();
        for (Event event : currUserEvents){
            if (event.getEventName().equals(eventName)){
                return event;
            }
        }
        return null;
    }

    /**
     * Return a list of events based on a list of event names
     * @param events the list of event names
     * @return the list of event instances
     */
    public List<Event> getEventsByNames(List<String> events){
        List<Event> result = new ArrayList<>();
        for (String event : events){
            if (getEventByName(event) != null){
                result.add(getEventByName(event));
            }
        }
        return result;
    }

    /**
     * Return the memo message corresponding to the given memo id
     * @param id the given memo id
     * @return the corresponding memo message
     */
    public String getMemoByID(int id){
        return memos.get(id);
    }

}
