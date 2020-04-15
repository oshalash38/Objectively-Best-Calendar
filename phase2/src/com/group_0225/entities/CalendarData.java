package com.group_0225.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarData {

    //contains mappings of ids to events (across all users and calendars)
    private User currUser;
    private String currCalendar;
    private Timing localTime;
    private Map<Integer, Event> events = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, String> memos = new HashMap<>();

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public Map<String, User> getUsers(){
        return users;
    }

    public Map<Integer, String> getMemos() {return memos;}

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
        currUser.addIdToCalendars(currCalendar, id);
    }

    public void addMemo(int id, String memo){
        memos.put(id, memo);
    }

    public void setEvents(Map<Integer, Event> events){
        this.events = events;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

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

    public void setMemos(Map<Integer, String> memos){
        this.memos = memos;
    }

    public Event getEventByName(String eventName){
        List<Event> currUserEvents = getCurrUserEvents();
        for (Event event : currUserEvents){
            if (event.getEventName().equals(eventName)){
                return event;
            }
        }
        return null;
    }

    public List<Event> getEventsByNames(List<String> events){
        List<Event> result = new ArrayList<>();
        for (String event : events){
            if (getEventByName(event) != null){
                result.add(getEventByName(event));
            }
        }
        return result;
    }

    public String getMemoByID(int id){
        return memos.get(id);
    }

}
