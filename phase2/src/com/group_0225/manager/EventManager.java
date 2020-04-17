package com.group_0225.manager;

import com.group_0225.entities.*;

import java.time.LocalDateTime;
import java.util.*;

public class EventManager {
    /**
     * Use case for events.
     * @author Omar Shalash
     */

    private static int idGen = 0;

    TimingFactory timingFactory;


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
        //System.out.println(id);
        event.setID(id);
        calendarData.addEvent(id, event);
        //TODO:testing only
        System.out.println(id + "\nStart:" + timing.getStart() + "\nEnd:" + timing.getEnd());
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
        //TODO:testing only
        System.out.println(id + "\nStart:" + timing.getStart() + "\nEnd:" + timing.getEnd());
        return event;
    }
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
        //getCurrCalendar is not static
//        List<Integer> userEvents = user.getEvents(CalendarController.getCurrCalendar());
//        events.put(idGen, e);
//        e.setID(idGen);
//        userEvents.add(idGen);
        return idGen;
    }

    /**
     * Changes the name of an event.
     * @param calendarData Calendar metadata.
     * @param event The event to be altered
     * @param newName The new name.
     */
    public void changeEventName(CalendarData calendarData, Event event, String newName, List<Event> events){
        Event currEvent = this.getEventByID(calendarData, event.getID());
        if (currEvent != null){
            currEvent.setEventName(newName);
        }
    }


    /**
     *
     * @param calendarData Calendar metadata.
     * @param name the name being searched
     * @return the Event matching the name
     */
    public Event searchEventByName(CalendarData calendarData, String name){
        for (Map.Entry<Integer,Event> eventEntry: calendarData.getEvents().entrySet()){
            if (eventEntry.getValue().getEventName().equals(name))
                 return eventEntry.getValue();
        }
        return null;
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
     * Returns a list of all the events that have the name sent in
     * @param user the user being searched in
     * @param name the name being looked for
     * @return the list of Events
     */
    public List<Event> searchEventsByName(User user, String name, List<Event> events){
        ArrayList<Event> output = new ArrayList<Event>();
        for (Event e: events){
            if (e.getEventName().equals(name))
                output.add(e);
        }
        return output;
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
     * @param data CalendarData instance
     * @param tag the tag being searched for
     * @return
     */
    public List<Event> getEventsByTag(String tag, CalendarData data){
//        HashMap<Integer,Event> events = (HashMap<Integer, Event>) user.getEvents();
//        List<Event> eventsByTag = new ArrayList<Event>();
//        for (Map.Entry<Integer, Event> entry: events.entrySet()){
//            if (entry.getValue().getTags().contains(tag)){
//                eventsByTag.add(entry.getValue());
//            }
//        }
//        return eventsByTag;
          List<Event> eventsByTag = new ArrayList<Event>();
          List<Event> userEvents = getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar());
          for(Event e: userEvents){
              if(e.getTags().contains(tag))
                  eventsByTag.add(e);
          }
          return eventsByTag;
    }
    public List<Event> getEventsBySeriesName(User user, String sname, List<Event> events){
        List<Event> snEvents = new ArrayList<Event>();
        for(Event e: events){
            if(e.getSeriesName().equals(sname))
               snEvents.add(e);
        }
        return snEvents;
    }
    public List<String> formatAllEventsForSeries(CalendarData data, UserManager um){
        List<Integer> ids = um.getIDs(data.getCurrUser(),data.getCurrCalendar());
        List<Event> events = new ArrayList<>();
        for (Integer i: ids){
            events.add(data.getEvents().get(i));
        }
        return formatEventsForSeries(events);
    }
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

    /**
     * Gets the tag of a passed event
     * @param e the event
     * @return string representing the tag of the event
     */
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

    public List<String> getNumEventsPerDay(CalendarData data, Timing threshold) {
        List<String> numPerDay = new ArrayList<>();

        for(List<Event> eventsOfDay : getEventsPerDay(data, threshold))
            numPerDay.add(eventsOfDay.size() + "");

        return numPerDay;
    }

    public List<String> getNumAlertsPerDay(CalendarData data, Timing threshold) {
        List<String> numPerDay = new ArrayList<>();

        for(List<Event> eventsOfDay : getEventsPerDay(data, threshold)){
            int alertsForDay = 0;

            for(Event e : eventsOfDay) {
                for(Alert alert : e.getAlerts()){
                    if(alert.getNextTime().intersect(threshold))
                        alertsForDay++;
                }
            }

            numPerDay.add(alertsForDay + "");
        }

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

    public List<String> getEventIDs(List<Event> events) {
        List<String> id = new ArrayList<>();

        for(Event e : events)
            id.add(e.getID() + "");

        return id;
    }
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
    public List<String> getEventsCurrUserByName(CalendarData data, String name){
        List<String> output = new ArrayList<>();
        for (Event e: getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar())){
            if (e.getEventName().equals(name)){
                output.add(e.getID().toString());
            }
        }
        return output;
    }
    public List<String> getEventIDsOfThreshold(CalendarData data, Timing threshold) {
        List<Event> events = getEventsBetween(data, threshold);
        System.err.println("Unpog?" + events.size());
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

    //TODO: complete this method... need some clarification about design.
        public void deleteEvent(Integer i, User u, SeriesManager sm){}
//        u.getEvents().remove(i);
//        if (!u.getEvents().get(i).getSeriesName().equals("")){
//            sm.checkAlone(u.getEvents().get(i).getSeriesName(), u);
//        }
//    }

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
                    events.remove(id);
            }
        }

        data.getEvents().remove(id);
    }

    public Event editEvent(CalendarData data, int id, String name, Timing timing) {
        Event event = data.getEvents().get(id);
        System.err.println(data.getEvents().get(0));

        event.setEventName(name);
        event.getTime().setStart(timing.getStart());
        event.getTime().setEnd(timing.getEnd());
        return event;
    }
    public void editEvent(CalendarData data, int id, String name, Timing timing,List<String> tags){
        Event e = editEvent(data, id, name, timing);
        e.setTags(tags);

    }
    /**
     * Required since Maps aren't sorted and events must be returned from earliest to latest
     * @param calendar a key-value entry in the User's calendars attribute
     * @return a sorted list of all events from the User's calendar
     */
    public List<Event> getSortedEvents(Map.Entry<String,List<Integer>> calendar, CalendarData calendarData){
        List<Integer> unsortedIDs = calendar.getValue();
        List<Event> unsortedEvents = new ArrayList<>();
        for (Integer i: unsortedIDs){
            unsortedEvents.add(calendarData.getEvents().get(i));
        }
        Collections.sort(unsortedEvents);
        return unsortedEvents;
    }

    public List<Event> getUserCalendarEvents(Map<Integer, Event> eventMap, User user, String calendarName) {
        List<Event> events = new ArrayList<>();

        for(int id : user.getEvents(calendarName))
            if(eventMap.containsKey(id))
                events.add(eventMap.get(id));

        return events;
    }

    public List<Alert> getAlerts(Event event){
        return event.getAlerts();
    }

    public void removeAlert(Event event, Alert alert){
        event.getAlerts().remove(alert);
    }

    public List<Event> getEventsByIDs(CalendarData data, List<String> eventIDs) {
        List<Event> result = new ArrayList<>();
        List<Event> events = data.getCurrUserEvents();
        for (Event event : events){
            if (eventIDs.contains(event.getID().toString())){
                result.add(event);
            }
        }
        return result;
    }
}
