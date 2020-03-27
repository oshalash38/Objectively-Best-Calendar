package com.group_0225;

import java.util.List;
import java.util.ArrayList;
public class EventManager {
    /**
     * Use case for events.
     * @author Omar Shalash
     */

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

    private void sortEvents(User user, Event e){
        ArrayList<Event> events = user.getEvents();

        for (Event event: events){
            if (event.compareTo(e) >= 0){ events.add(events.indexOf(event), e); return;}
        }
        events.add(e);
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
        ArrayList<Event> events = user.getEvents();
        for (Event event: events){
            if (event.getEventName().equals(name)){
                return event;
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
        ArrayList<Event> events = user.getEvents();
        ArrayList<Event> output = new ArrayList<Event>();
        for (Event event: events){
            if (event.getEventName().equals(name)){
                output.add(event);
            }
        }
        return output;
    }

    /**
     * Updates the status of every event stored in the user.
     * @param user The user.
     */
    public void updateStatus(User user){
        ArrayList<Event> events = user.getEvents();
        for (Event event : events){
            event.updateStatus();
        }
    }

    /**
     * Returns a list of upcoming events
     * @param user the user that this method looks in
     * @return the list of upcoming events
     */
    public ArrayList<Event> getUpcomingEvents(User user){
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.UPCOMING){
                upcomingEvents.add(event);
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
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.CURRENT){
                currentEvents.add(event);
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
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.PAST){
                pastEvents.add(event);
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
        List<Event> events = user.getEvents();
        List<Event> eventsByTag = new ArrayList<Event>();
        for(Event e: events){
            if (e.getTags().contains(tag)){
                eventsByTag.add(e);
            }
        }
        return eventsByTag;
    }
    public List<Event> getEventsBySeriesName(User user, String sname){
        List<Event> events = user.getEvents();
        List<Event> snEvents = new ArrayList<>();
        for (Event e: events){
            if (e.getSeriesName().equals(sname)){
                snEvents.add(e);
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
        List<Event> events = currUser.getEvents();
        for (Event event : events){
            if (event.compareTo(date1) == 1 || event.compareTo(date1) == 1){
                out.add(event);
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
    public void deleteEvent(Event e, User u, SeriesManager sm){
        u.getEvents().remove(e);
        if (!e.getSeriesName().equals("")){
            sm.checkAlone(e.getSeriesName(), u);
        }
    }
}
