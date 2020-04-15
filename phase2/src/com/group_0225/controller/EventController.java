package com.group_0225.controller;

import com.group_0225.entities.*;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.MemoManager;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.UIPresenter;

import java.time.LocalDateTime;
import java.util.*;

public class EventController extends CalendarController {

    private EventManager eventManager;
    private MemoManager memoManager;

    // TODO: Not sure if other classes will need this or not as well to avoid duplicate code.




    public EventController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        eventManager = new EventManager();
        memoManager = new MemoManager();
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
    public void viewEventByStatus(Status status){
        eventManager.updateStatus(data);
        List<Event> events = eventManager.getEventsByStatus(data, status);
        presenter.updateUI(new UIUpdateInfo("dialog", eventManager.getEventIDs(events) , "EventListPanel"));

    }

    public void viewEventByDateThreshold(){
        eventManager.updateStatus(data);
        presenter.updateUI(new UIUpdateInfo("dialog", null, "DateThresholdPanel"));
    }

    /**
     * Creates a new event.
     * @param input : index 0: Name, index 1: Start Date, index 2: End Date,
     *              index 3: Start Time, index 4: End Time
     */
    public void createEvent(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input.get(0).equals("Error1")){
            output.add("Error1");
        } else {
            TimingFactory timingFactory = new TimingFactory();

            List<Integer> startDateParsed = parseDate(input.get(1));
            List<Integer> endDateParsed = parseDate(input.get(2));
            List<Integer> startTimeParsed = parseTime(input.get(3));
            List<Integer> endTimeParsed = parseTime(input.get(4));
            Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
                    startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                    endTimeParsed.get(0), endTimeParsed.get(1));
            if (timing == null){
                output.add("Error2");
            }
            else {
                eventManager.createEvent(data, input.get(0), timing);
                output.add("Created");
            }
        }
        presenter.updateUI(new UIUpdateInfo("dialog", output, "CreateEventPanel"));
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
        List<String> memos = memoManager.getMemos(event, data);
        List<String> output = new ArrayList<>();
        output.add(event.getEventName());
        output.add(event.getStartDateString());
        output.add(event.getEndDateString());
        output.add(event.getStartTimeString());
        output.add(event.getEndTimeString());
        output.add(event.getSeriesName());
        output.addAll(memos);
        presenter.updateUI(new UIUpdateInfo("dialog", output, "EventPanel"));
    }

    public void getEventsByDateThreshold(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input.get(0).equals("Error1")){
            output.add("Error1");
            presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
        }else if (input.get(0).equals("Error2")){
            output.add("Error2");
            presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
        }
        else {
            TimingFactory timingFactory = new TimingFactory();

            List<Integer> startDateParsed = parseDate(input.get(0));
            List<Integer> endDateParsed = parseDate(input.get(1));
            List<Integer> startTimeParsed = parseTime(input.get(2));
            List<Integer> endTimeParsed = parseTime(input.get(3));
            Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
                    startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                    endTimeParsed.get(0), endTimeParsed.get(1));
            if (timing == null){
                output.add("Error2");
                presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
            }
            else {
                output = eventManager.getEventIDsOfThreshold(data, timing);
                presenter.updateUI(new UIUpdateInfo("dialog", output, "EventListPanel"));
            }
        }
    }
}
