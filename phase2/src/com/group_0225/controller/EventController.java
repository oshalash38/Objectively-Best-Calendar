package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.Timing;
import com.group_0225.entities.TimingFactory;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.time.LocalDateTime;
import java.util.*;

public class EventController extends CalendarController {

    private EventManager eventManager;

    // TODO: Not sure if other classes will need this or not as well to avoid duplicate code.




    public EventController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        eventManager = new EventManager();
    }




    public void pushCreateEvent(){
        presenter.updateUI(new UIUpdateInfo("dialog", null, "CreateEventPanel"));
    }
    public void viewEvents(String rawDay, String rawMonth, String rawYear){
        int day = Integer.parseInt(rawDay);
        int month = Integer.parseInt(rawMonth);
        int year = Integer.parseInt(rawYear);

        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59);

        Timing threshold = new Timing(start, end);

        presenter.updateUI(new UIUpdateInfo("dialog", eventManager.getEventIDsOfThreshold(data, threshold), "EventListPanel"));
    }

    /**
     * Shows the user past events, current events, or future events
     * @param status -1: past events
     *                0: current events
     *                1: future events
     */
    public void viewEventByStatus(int status){
        eventManager.updateStatus(data);
        List<Event> events;
        switch (status){
            case 1:
                events = eventManager.getUpcomingEvents(data);
                break;
            case -1:
                events = eventManager.getPastEvents(data);
                break;
            case 0:
                events = eventManager.getCurrentEvents(data);
                break;
            default:
                events = new ArrayList<>();
        }
        presenter.updateUI(new UIUpdateInfo("dialog", getEventsName(events) , "EventListPanel"));

    }

    /**
     * Creates a new event.
     * @param input : index 0: Name, index 1: Start Date, index 2: End Date,
     *              index 3: Start Time, index 4: End Time
     */
    public void createEvent(List<String> input) {
        TimingFactory timingFactory = new TimingFactory();

        List<Integer> startDateParsed = parseDate(input.get(1));
        List<Integer> endDateParsed = parseDate(input.get(2));
        List<Integer> startTimeParsed = parseTime(input.get(3));
        List<Integer> endTimeParsed = parseTime(input.get(4));
        Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
           startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                endTimeParsed.get(0), endTimeParsed.get(1));
        eventManager.createEvent(data, input.get(0), timing);
    }

    public void pushViewEventsByDateThreshold(){
        //Display a dialog that asks user to type in the date threshold that they want
    }

    public void viewEventsByDateThreshold(List<String> input){
        // parse it
        // get the events you need from the EventManager
        // parse the events into string format
        // push a dialog with the corresponding events  presenter.displayPanel( new PanelInfo(PanelKey, List of string representation of the events (probably just their names), true)
    }

    /**
     * Parses an input date from panel
     * @param s
     * @return index 0: Day, index 1: Month, index 2: Year
     */
    private List<Integer> parseDate(String s) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 11){
            result.add(Integer.parseInt(s.substring(0, 2)));
            result.add(Months.get(s.substring(3, 6)));
            result.add(Integer.parseInt(s.substring(7, 11)));
        } else {
            result.add(Integer.parseInt(s.substring(0, 1)));
            result.add(Months.get(s.substring(2, 5)));
            result.add(Integer.parseInt(s.substring(6, 10)));
        }
        return result;
    }

    /**
     * Parses an input time from panel
     * @param s
     * @return index 0: Hour, index 1: Minute, index 3: Second
     */
    private List<Integer> parseTime(String s){
        List<Integer> result = new ArrayList<>();
        result.add(Integer.parseInt(s.substring(0, 2)));
        result.add(Integer.parseInt(s.substring(3, 5)));
        result.add(Integer.parseInt(s.substring(6, 8)));
        return result;
    }


    // TODO: Should this be here or in manager?
    private List<String> getEventsName(List<Event> events){
        List<String> result = new ArrayList<>();
        for (Event e : events){
            System.out.println(e.getEventName());
            result.add(e.getEventName());
        }
        return result;
    }

    public String getEventName(String rawId) {
        int id = Integer.parseInt(rawId);
        Event event = eventManager.getEventByID(data, id);

        return event.getEventName();
    }

    public void displayEvent(String rawID){
        int id = Integer.parseInt(rawID);
        Event event = eventManager.getEventByID(data, id);
        List<String> output = new ArrayList<>();
        output.add(event.getEventName());
        output.add(event.getStartDateString());
        output.add(event.getEndDateString());
        output.add(event.getStartTimeString());
        output.add(event.getEndTimeString());
        output.add(event.getSeriesName());
        presenter.updateUI(new UIUpdateInfo("dialog", output, "EventPanel"));
    }
}
