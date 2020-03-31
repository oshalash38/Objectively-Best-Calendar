package com.group_0225.manager;

import com.group_0225.Alert;
import com.group_0225.Event;

import java.util.HashMap;
import java.util.Map;

public class CalendarData {

    //contains mappings of ids to events (across all users and calendars)
    private Map<Integer, Event> events = new HashMap<>();
    private Map<Integer, Alert> alerts = new HashMap<>();
    private Map<Integer, String> memos = new HashMap<>();
    //TODO Still needs to be implemented. Waiting for key system for events
    public Map<Integer, Event> getEvents() {
        return events;
    }

    public  Map<Integer, String> getMemos(){return memos;}
}
