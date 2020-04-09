package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.Timing;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.util.*;

public class EventController extends CalendarController {

    private EventManager eventManager;

    // TODO: Not sure if other classes will need this or not as well to avoid duplicate code.
    private Map<String, Integer> Months;



    public EventController(CalendarData data, UIPresenter presenter, Timing localTime) {
        super(data, presenter, localTime);
        Months = new HashMap<>();
        buildMonths();
        eventManager = new EventManager();
    }

    private void buildMonths() {
        Months.put("Jan", 1);
        Months.put("Feb", 2);
        Months.put("Mar", 3);
        Months.put("Apr", 4);
        Months.put("May", 5);
        Months.put("Jun", 6);
        Months.put("Jul", 7);
        Months.put("Aug", 8);
        Months.put("Sep", 9);
        Months.put("Oct", 10);
        Months.put("Nov", 11);
        Months.put("Dec", 12);

    }


    public void pushCreateEvent(){
        presenter.displayPanel(new PanelInfo("CreateEventPanel", null, true));
    }
    public void viewEvents(){
        presenter.displayPanel(new PanelInfo("EventListPanel", null, true));
    }

    /**
     * Shows the user past events, current events, or future events
     * @param status -1: past events
     *                0: current events
     *                1: future events
     */
    public void viewEventByStatus(int status){
        switch (status){
            case 1:
                List<Event> events = eventManager.getUpcomingEvents(data);
                presenter.displayPanel(new PanelInfo("EventListPanel", getEventsName(events) , true));
                break;
        }

    }

    /**
     * Creates a new event.
     * @param input : index 0: Name, index 1: Start Date, index 2: End Date,
     *              index 3: Start Time, index 4: End Time
     */
    public void createEvent(List<String> input) {
        List<Integer> startDateParsed = parseDate(input.get(1));
        List<Integer> endDateParsed = parseDate(input.get(2));
        List<Integer> startTimeParsed = parseTime(input.get(3));
        List<Integer> endTimeParsed = parseTime(input.get(4));
        Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
           startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                endTimeParsed.get(0), endTimeParsed.get(1));
        eventManager.createEvent(data, input.get(0), timing);
    }

    /**
     * Parses an input date from panel
     * @param s
     * @return index 0: Day, index 1: Month, index 2: Year
     */
    private List<Integer> parseDate(String s) {
        List<Integer> result = new ArrayList<>();
        result.add(Integer.parseInt(s.substring(0, 2)));
        result.add(Months.get(s.substring(3, 6)));
        result.add(Integer.parseInt(s.substring(7, 11)));
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
}
