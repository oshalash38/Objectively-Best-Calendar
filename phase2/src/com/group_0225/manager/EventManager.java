package com.group_0225.manager;

import com.group_0225.entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager {
    /**
     * Use case for events.
     * @author Omar Shalash
     */

    private static int idGen = 0;

    TimingFactory timingFactory;


    /**
     * Creates a new EventManager
     */
    public EventManager(){
        timingFactory = new TimingFactory();
    }

    /**
     * Creates a new event and returns it.
     * @param calendarData The user that will store the new event.
     * @param name Name of the event.
     * @param timing Timing of the event.
     */
    public Event createEvent(CalendarData calendarData, String name, Timing timing){
        Event event = new Event(name, timing);
        int id = sortEvents(calendarData);
        event.setID(id);
        calendarData.addEvent(id, event);
        return event;
    }

    /**
     * Creates a new event and returns it.
     * @param calendarData The user that will store the new event.
     * @param name Name of the event.
     * @param timing Timing of the event.
     * @param series series of the event.
     */
    public Event createEvent(CalendarData calendarData, String name, Timing timing, String series){
        Event event = new Event(name, timing, series);
        int id = sortEvents(calendarData);
        event.setID(id);
        calendarData.addEvent(id, event);
        return event;
    }

    /**
     * Creates an event as specified
     * @param calendarData the data for this Calendar
     * @param name         The name of this new event
     * @param timing       The start time and date time of this event
     * @param tags         The tags associated with this event
     * @return
     */
    public Event createEvent(CalendarData calendarData, String name, Timing timing,List<String> tags){
        Event e = createEvent(calendarData,name,timing);
        e.addTags(tags);
        return e;
    }

    /**
     * Sorts the events by generating a unique sequential id for the event.
     * @param calendarData the metadata of the calendar
     * @return the id generated
     */
    private int sortEvents(CalendarData calendarData){
        Map<Integer,Event> events = calendarData.getEvents();
        int high = -1;
        for (Map.Entry<Integer, Event> entry: events.entrySet()){
            if(entry.getKey() > high){high = entry.getKey();}
        }
        idGen = high + 1;
        return idGen;
    }

    /**
     * Gets an event stored by its id
     * @param data the calendar metadata
     * @param id the id of the event
     * @return the event if found, null otherwise
     */
    public Event getEventByID(CalendarData data, int id) {
        if(data.getEvents().containsKey(id))
            return data.getEvents().get(id);

        return null;
    }

    /**
     * Updates the status of every event stored in the user.
     * @param calendarData The calendar metadata.
     */
    public void updateStatus(CalendarData calendarData){
        for(Map.Entry<Integer, Event> entry : calendarData.getEvents().entrySet()){
            entry.getValue().updateStatus(calendarData);
        }
    }

    /**
     * Getter for events according to their status (Current/Upcoming/Past)
     * @param data The calendar metadata.
     * @param status the status to be searched
     * @return list of events of the given status
     */
    public List<Event> getEventsByStatus(CalendarData data, Status status) {
        List<Event> eventsOfStatus = new ArrayList<>();

        List<Event> userEvents = data.getCurrUserEvents();
        for(Event e : userEvents)
            if(e.getStatus() == status)
                eventsOfStatus.add(e);

        return eventsOfStatus;
    }


    /**
     * Return a list of events that are associated with a given tag
     * @param data CalendarData instance
     * @param tag the tag being searched for
     * @return
     */
    public List<Event> getEventsByTag(String tag, CalendarData data){
          List<Event> eventsByTag = new ArrayList<Event>();
          List<Event> userEvents = getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar());
          for(Event e: userEvents){
              if(e.getTags().contains(tag))
                  eventsByTag.add(e);
          }
          return eventsByTag;
    }

    /**
     * Returns the events of this series
     * @param sname the series name
     * @param events the events that need to be checked
     * @return The lis of events that are part of this series
     */
    public List<Event> getEventsBySeriesName(String sname, List<Event> events){
        List<Event> snEvents = new ArrayList<Event>();
        for(Event e: events){
            if(e.getSeriesName().equals(sname))
               snEvents.add(e);
        }
        return snEvents;
    }


    /**
     * Adds events to a series
     * @param data the CalendarData where everything is stored
     * @param um the UserManager for user sue casses
     * @return The strings of the list of events
     */
    public List<String> formatAllEventsForSeries(CalendarData data, UserManager um){
        List<Integer> ids = um.getIDs(data.getCurrUser(),data.getCurrCalendar());
        List<Event> events = new ArrayList<>();
        for (Integer i: ids){
            events.add(data.getEvents().get(i));
        }
        return formatEventsForSeries(events);
    }

    /**
     * Formats the events with their info to be displayed
     * @param events the list of events to consider
     * @return the string representartions
     */
    public List<String> formatEventsForSeries(List<Event> events){
        List<String> output = new ArrayList<>();
        for (Event e: events){
            output.add("Name: " + e.getEventName());
            output.add("\nStart: " + e.getStartDateString() + " " + e.getStartTimeString());
            output.add("\nEnd: " + e.getEndDateString() + " " + e.getEndTimeString());
            output.add("\nSeries: " + e.getSeriesName());
            output.add(e.getID().toString());
        }
        return output;
    }

    /**Return a list of user's events between two dates
     *
     * @param data the calendar data
     * @param date the first date
     * @return a list of events between date1 and date2
     */
    public List<Event> getEventsBetween(CalendarData data, Timing date) {
        List<Event> events = getUserCalendarEvents(data.getEvents(), data.getCurrUser(), data.getCurrCalendar());

        List<Event> out = new ArrayList<>();
        for(Event e: events){
            if (e.getTime().intersect(date) && !out.contains(e))
                out.add(e);
        }
        return out;
    }

    /**
     * Returns a list of events that occur on each day
     * @param data the data where the events are stored
     * @param threshold the threshold to check between
     * @return A list of events for each given day
     */
    public List<List<Event>> getEventsPerDay(CalendarData data, Timing threshold) {
        List<List<Event>> numOfEvents = new ArrayList<>();

        LocalDateTime startingDisplay = threshold.getStart();
        LocalDateTime currStartTime = LocalDateTime.of(startingDisplay.getYear(), startingDisplay.getMonth(), 1, 0, 0);

        LocalDateTime thresholdEnd = threshold.getEnd() == null ? threshold.getStart() : threshold.getEnd();

        while(currStartTime.compareTo(thresholdEnd) < 0) {
            LocalDateTime currEndTime = LocalDateTime.of(startingDisplay.getYear(), startingDisplay.getMonth(), currStartTime.getDayOfMonth(), 23, 59);

            Timing timingOfDay = timingFactory.createTiming(currStartTime);
            timingOfDay.setEnd(currEndTime);

            List<Event> eventsOfDay = getEventsBetween(data, timingOfDay);
            numOfEvents.add(eventsOfDay);

            currStartTime = currStartTime.plusDays(1);
        }

        return numOfEvents;
    }

    /**
     * Returns the number of events per day
     * @param data the CalendarData where the events are stored
     * @param threshold the threshold to check between
     * @return the number of events per day
     */
    public List<String> getNumEventsPerDay(CalendarData data, Timing threshold) {
        List<String> numPerDay = new ArrayList<>();

        for(List<Event> eventsOfDay : getEventsPerDay(data, threshold))
            numPerDay.add(eventsOfDay.size() + "");

        return numPerDay;
    }

    /**
     * Gets a list of name of events from list of events
     * @param events the list of events
     * @return the list of the names of the input events
     */
    public List<String> getNames(List<Event> events) {
        List<String> names = new ArrayList<>();
        for(Event e : events)
            names.add(e.getEventName() + "");
        return names;
    }

    /**
     * Return a list of IDs for a given list of events
     * @param events the list of events whose IDs are needed
     * @return the list of IDs
     */
    public List<String> getEventIDs(List<Event> events) {
        List<String> id = new ArrayList<>();
        for(Event e : events)
            id.add(e.getID() + "");
        return id;
    }

    /**
     * Return a list of all the tags associated with all the given events
     * @param events the events whose tags are needed
     * @return the list of tags
     */
    public List<String> getAllTags(List<Event> events){
        List<String> tags = new ArrayList<>();
        for (Event e: events){
            for (String tag: e.getTags()){
                if (!tags.contains(tag)){
                    tags.add(tag);
                }
            }
        }
        return tags;
    }

    /**
     * Return the IDs for the events that have this name
     * @param data the CalendarData where the events are stored
     * @param name the name being searched for
     * @return the IDs that have this name
     */
    public List<String> getEventsCurrUserByName(CalendarData data, String name){
        List<String> output = new ArrayList<>();
        for (Event e: getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar())){
            if (e.getEventName().equals(name)){
                output.add(e.getID().toString());
            }
        }
        return output;
    }

    /**
     *
     * @param data
     * @param threshold
     * @return
     */
    public List<String> getEventIDsOfThreshold(CalendarData data, Timing threshold) {
        List<Event> events = getEventsBetween(data, threshold);
        return getEventIDs(events);
    }

    /**
     *
     * @param e the event to add the tag to
     * @param newTag the string that is required for the tag creation
     */
    public void addTag(Event e, String newTag){
        e.getTags().add(newTag);
    }

    /**
     * Method for deleting a specific event.
     * @param data the metadata of the calendar
     * @param id the id of the event to be deleted
     */
    public void deleteEvent(CalendarData data, int id) {
        for(User u : data.getUsers().values()){
            for(String calendar : u.getCalendars()){
                List<Integer> events = u.getEvents(calendar);
                if(events.contains(id))
                    events.remove((Integer)id);
            }
        }

        data.getEvents().remove(id);
    }

    /**
     * Overwrite the data of this event
     * @param data where the event is stored
     * @param id The id of this event
     * @param name the name of this event
     * @param timing the timing of this event
     * @return the new edited event
     */
    public Event editEvent(CalendarData data, int id, String name, Timing timing) {
        Event event = data.getEvents().get(id);

        event.setEventName(name);
        event.getTime().setStart(timing.getStart());
        event.getTime().setEnd(timing.getEnd());
        return event;
    }
    /**
     * Overwrite the data of this event with tags
     * @param data where the event is stored
     * @param id The id of this event
     * @param name the name of this event
     * @param timing the timing of this event
     * @param tags the tags of this event
     * @return the new edited event
     */
    public void editEvent(CalendarData data, int id, String name, Timing timing,List<String> tags){
        Event e = editEvent(data, id, name, timing);
        e.setTags(tags);
    }

    /**
     * Return  a list of events for a given calendar
     *
     * @param eventMap the map where events are stored
     * @param user the user responsible for these events
     * @param calendarName The calendar being used at the moment
     * @return the list of events
     */
    public List<Event> getUserCalendarEvents(Map<Integer, Event> eventMap, User user, String calendarName) {
        List<Event> events = new ArrayList<>();

        for(int id : user.getEvents(calendarName))
            if(eventMap.containsKey(id))
                events.add(eventMap.get(id));

        return events;
    }

    /**
     * returns the alerts for this event
     * @param event the event that is being accessed
     * @return the alerts for this event
     */
    public List<Alert> getAlerts(Event event){
        return event.getAlerts();
    }

    /**
     * Removes a given alert from an event
     * @param event the event
     * @param alert the alert
     */
    public void removeAlert(Event event, Alert alert){
        event.getAlerts().remove(alert);
    }
}
