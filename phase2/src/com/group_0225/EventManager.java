package com.group_0225;

import com.group_0225.manager.CalendarData;

import java.util.*;

public class EventManager {
    /**
     * Use case for events.
     * @author Omar Shalash
     */

    private static int idGen = 0;
    private CalendarData cd;

    public EventManager(){
        cd = new CalendarData();
    }

    /**
     * Creates a new event and returns it.
     * @param user The user that will store the new event.
     * @param name Name of the event.
     * @param timing Timing of the event.
     */
    public Event createEvent(User user, String name, Timing timing){
        Event event = new Event(name, timing);
        sortEvents(user, event);
        return event;
    }

    /**
     * Creates a new event and returns it.
     * @param user The user that will store the new event.
     * @param name Name of the event.
     * @param timing Timing of the event.
     * @param series series of the event.
     */
    public Event createEvent(User user, String name, Timing timing, String series){
        Event event = new Event(name, timing, series);
        sortEvents(user,event);
        return event;
    }

    private int sortEvents(User user, Event e){
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
        int high = -1;
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if(entry.getKey() > high){high = entry.getKey();}
        }
        idGen = high + 1;
        events.put(idGen, e);
        return idGen;
    }


    /**
     * Changes the name of an event.
     * @param user The user that contains the event to be altered
     * @param event The event to be altered
     * @param newName The new name.
     */
    public void changeEventName(User user, Event event, String newName){
        Event currEvent = this.searchEventByName(user, event.getEventName());
        if (currEvent != null){
            currEvent.setEventName(newName);
        }
    }


    /**
     *
     * @param user the user that is being manipulated
     * @param name the name being searched
     * @return the Event matching the name
     */
    public Event searchEventByName(User user, String name){
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (entry.getValue().getEventName().equals(name)){
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Returns a list of all the events that have the name sent in
     * @param user the user being searched in
     * @param name the name being looked for
     * @return the list of Events
     */
    public List<Event> searchEventsByName(User user, String name){
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
        ArrayList<Event> output = new ArrayList<Event>();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (entry.getValue().getEventName().equals(name)){
                output.add(entry.getValue());
            }
        }
        return output;
    }

    /**
     * Updates the status of every event stored in the user.
     * @param user The user.
     */
    public void updateStatus(User user){
        Map<Integer,Event> events = user.getEvents();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            entry.getValue().updateStatus();
        }
    }

    /**
     * Returns a list of upcoming events
     * @param user the user that this method looks in
     * @return the list of upcoming events
     */
    public ArrayList<Event> getUpcomingEvents(User user){
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        HashMap<Integer,Event> allEvents = (HashMap<Integer, Event>) user.getEvents();
        for (Map.Entry<Integer, Event> entry: allEvents.entrySet()){
            if (entry.getValue().getStatus() == Status.UPCOMING){
                upcomingEvents.add(entry.getValue());
            }
        }
        return upcomingEvents;
    }

    /**
     * Returns a list of events that are currently happening
     * @param user the user that this method looks in
     * @return the list of current events
     */
    public ArrayList<Event> getCurrentEvents(User user){
        ArrayList<Event> currentEvents = new ArrayList<>();
        HashMap<Integer,Event> allEvents = (HashMap<Integer, Event>) user.getEvents();
        for (Map.Entry<Integer, Event> entry: allEvents.entrySet()){
            if (entry.getValue().getStatus() == Status.CURRENT){
                currentEvents.add(entry.getValue());
            }
        }
        return currentEvents;
    }

    /**
     * Returns a list of past events
     * @param user the user that this method looks in
     * @return the list of past events
     */
    public ArrayList<Event> getPastEvents(User user){
        ArrayList<Event> pastEvents = new ArrayList<>();
        HashMap<Integer,Event> allEvents = (HashMap<Integer, Event>) user.getEvents();
        for (Map.Entry<Integer, Event> entry: allEvents.entrySet()){
            if (entry.getValue().getStatus() == Status.PAST){
                pastEvents.add(entry.getValue());
            }
        }
        return pastEvents;
    }

    /**
     * Formats the events by name. Returns only the name of the requested events
     *
     * @param events the events that need to be formatted
     * @return a list of the names of the strings provided
     */
    public List<String> formatEventByName(List<Event> events ){
        List<String> nameList = new ArrayList<String>();
        for(Event event: events){
            nameList.add(event.getEventName());
        }
        return nameList;
    }

    /**
     * Retrurn a list of events that are associated with a given tag
     * @param user the user that has all these events
     * @param tag the tag being searched for
     * @return
     */
    public List<Event> getEventsByTag(User user, String tag){
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
        List<Event> eventsByTag = new ArrayList<Event>();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (entry.getValue().getTags().contains(tag)){
                eventsByTag.add(entry.getValue());
            }
        }
        return eventsByTag;
    }
    public List<Event> getEventsBySeriesName(User user, String sname){
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
        List<Event> snEvents = new ArrayList<>();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (entry.getValue().getSeriesName().equals(sname)){
                snEvents.add(entry.getValue());
            }
        }return snEvents;
    }

    /**
     * Returns a detailed view of this event
     * @param e the event
     * @param alertManager the way to interact with alerts
     * @return
     */
    public List<String> getDetailedEvent(Event e, AlertManager alertManager){
        List<String> content = new ArrayList<String>();
        content.add(e.getEventName());
        if(e.getSeriesName().equals(""))
            content.add("No series");
        else{
            content.add(e.getSeriesName());
        }
        content.add(e.getStartTimeString());
        content.add(e.getEndTimeString());
        content.add(alertManager.formatReminders(e));
        content.add(getTags(e));
        return content;
    }

    private String getTags(Event e){
        String str = "";
        if(e.getTags().isEmpty())
            return "No tags";
        for(String tag: e.getTags()){
            str+=tag + ", ";
        }
        return str;
    }

    /**Return a list of user's events between two dates
     *
     * @param currUser user passed
     * @param date1 the first date
     * @param date2 the second date
     * @return a list of events between date1 and date2
     */
    public List<Event> getEventsBetween(User currUser, Timing date1, Timing date2) {
        List<Event> out = new ArrayList<>();
        HashMap<Integer,Event> events = (HashMap<Integer, Event>) currUser.getEvents();
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if (entry.getValue().compareTo(date1) == 1 || entry.getValue().compareTo(date1) == 1){
                out.add(entry.getValue());
            }
        }
        return out;
    }

    /**
     *
     * @param e the event to add the tag to
     * @param newTag the string that is required for the tag creation
     */
    public void addTag(Event e, String newTag){
        e.getTags().add(newTag);
    }
    public void deleteEvent(Integer i, User u, SeriesManager sm){
        u.getEvents().remove(i);
        if (!u.getEvents().get(i).getSeriesName().equals("")){
            sm.checkAlone(u.getEvents().get(i).getSeriesName(), u);
        }
    }

    /**
     * Required since Maps aren't sorted and events must be returned from earliest to latest
     * @param calendar a key-value entry in the User's calendars attribute
     * @return a sorted list of all events from the User's calendar
     */
    public List<Event> getSortedEvents(Map.Entry<String,List<Integer>> calendar){
        List<Integer> unsortedIDs = calendar.getValue();
        List<Event> unsortedEvents = new ArrayList<>();
        for (Integer i: unsortedIDs){
            unsortedEvents.add(cd.getEvents().get(i));
        }
        Collections.sort(unsortedEvents);
        return unsortedEvents;
    }
}
