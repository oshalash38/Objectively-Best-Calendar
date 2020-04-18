package com.group_0225.controller;

import com.group_0225.entities.*;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.MemoManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Controls high-level logic with respect to events
 */
public class EventController extends CalendarController {

    private EventManager eventManager;
    private MemoManager memoManager;

    /**
     * Constructs an EventController instance
     *
     * @param data      a CalendarData instance
     * @param presenter a UIPresenter instance
     */
    public EventController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        eventManager = new EventManager();
        memoManager = new MemoManager();
    }

    /**
     * Displays a dialog that allows the user to create a new event
     */
    public void pushCreateEvent() {
        presenter.updateUI(new UIUpdateInfo("dialog", null, "CreateEventPanel"));
    }

    /**
     * Display a dialog that allows the user to edit one of their events
     *
     * @param rawId the id of the event being edited
     */
    public void pushEditEvent(String rawId) {
        Event e = data.getEvents().get(Integer.parseInt(rawId));

        List<String> input = new ArrayList<>();
        input.add("Edit");
        input.add(e.getEventName());

        Timing eventTiming = e.getTime();
        LocalDateTime start = eventTiming.getStart();
        input.add(start.getYear() + "");
        input.add((start.getMonthValue() - 1) + "");
        input.add(start.getDayOfMonth() + "");
        input.add(start.getHour() + "");
        input.add(start.getMinute() + "");
        LocalDateTime end = eventTiming.getEnd();
        input.add(end.getYear() + "");
        input.add((end.getMonthValue() - 1) + "");
        input.add(end.getDayOfMonth() + "");
        input.add(end.getHour() + "");
        input.add(end.getMinute() + "");

        //number of tags
        input.add(Integer.toString(e.getTags().size()));
        //tags
        input.addAll(e.getTags());

        input.add(rawId);

        presenter.updateUI(new UIUpdateInfo("dialog", input, "CreateEventPanel"));
    }

    /**
     * Edits an event
     *
     * @param inputs         includes any error messages and info required to create the event
     * @param gridController a CalendarGridController instance
     */
    public void editEvent(List<String> inputs, CalendarGridController gridController) {
        List<String> output = new ArrayList<>();
        if (inputs.get(0).equals("Error1")) {
            output.add("Error1");
        } else {
            TimingFactory timingFactory = new TimingFactory();

            List<Integer> startDateParsed = parseDate(inputs.get(1));
            List<Integer> endDateParsed = parseDate(inputs.get(2));
            List<Integer> startTimeParsed = parseTime(inputs.get(3));
            List<Integer> endTimeParsed = parseTime(inputs.get(4));
            Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
                    startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                    endTimeParsed.get(0), endTimeParsed.get(1));
            List<String> tags = Arrays.asList(inputs.get(5).split("\n"));
            if (timing == null) {
                output.add("Error2");
            } else {
                int id = Integer.parseInt(inputs.get(inputs.size() - 1));
                eventManager.editEvent(data, id, inputs.get(0), timing, tags);
                output.add("Edited");
            }
        }
        presenter.updateUI(new UIUpdateInfo("dialog", output, "CreateEventPanel"));
        gridController.displayGrid();
    }

    /**
     * Displays events for the specific point in time given (all input is within the correct bounds)
     *
     * @param rawDay   a day
     * @param rawMonth a month
     * @param rawYear  a year
     */
    public void viewEvents(String rawDay, String rawMonth, String rawYear) {
        int day = Integer.parseInt(rawDay);
        int month = Integer.parseInt(rawMonth);
        int year = Integer.parseInt(rawYear);

        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59);

        Timing threshold = new Timing(start, end);

        presenter.updateUI(new UIUpdateInfo("scrollable", eventManager.getEventIDsOfThreshold(data, threshold), "EventListPanel"));
    }

    /**
     * Shows the user past events, current events, or future events
     *
     * @param status -1: past events
     *               0: current events
     *               1: future events
     */
    public void viewEventByStatus(Status status) {
        eventManager.updateStatus(data);
        List<Event> events = eventManager.getEventsByStatus(data, status);
        presenter.updateUI(new UIUpdateInfo("dialog", eventManager.getEventIDs(events), "EventListPanel"));

    }

    /**
     * Displays a dialog that allows the user to view events by a particular date threshold
     */
    public void viewEventByDateThreshold() {
        eventManager.updateStatus(data);
        presenter.updateUI(new UIUpdateInfo("dialog", null, "DateThresholdPanel"));
    }

    /**
     * Creates a new event.
     *
     * @param input : index 0: Name, index 1: Start Date, index 2: End Date,
     *              index 3: Start Time, index 4: End Time
     */
    public void createEvent(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input.get(0).equals("Error1")) {
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
            List<String> tags = Arrays.asList(input.get(5).split("\n"));
            if (timing == null) {
                output.add("Error2");
            } else {
                eventManager.createEvent(data, input.get(0), timing, tags);
                output.add("Created");
            }
        }
        presenter.updateUI(new UIUpdateInfo("dialog", output, "CreateEventPanel"));
    }

    /**
     * Controls the high-level logic for deleting an event
     *
     * @param rawId          the id of an event as a String
     * @param gridController a CalendarGridController instance
     */
    public void deleteEvent(String rawId, CalendarGridController gridController) {
        int id = Integer.parseInt(rawId);

        eventManager.deleteEvent(data, id);

        gridController.displayGrid();
    }

    /**
     * Get the name of an event given its id
     *
     * @param rawId the id of the event, as a String
     * @return the name of the event
     */
    public String getEventName(String rawId) {
        int id = Integer.parseInt(rawId);
        Event event = eventManager.getEventByID(data, id);

        return event.getEventName();
    }

    /**
     * Displays a dialog containing the info of the event of the id given
     *
     * @param rawID given id as a String
     */
    public void displayEvent(String rawID) {
        int id = Integer.parseInt(rawID);
        Event event = eventManager.getEventByID(data, id);
        List<String> memos = memoManager.getMemos(event, data);
        List<String> output = new ArrayList<>();
        output.add(rawID);
        output.add(event.getEventName());
        output.add(event.getStartDateString());
        output.add(event.getEndDateString());
        output.add(event.getStartTimeString());
        output.add(event.getEndTimeString());
        output.add(event.getSeriesName());
        output.addAll(memos);
        presenter.updateUI(new UIUpdateInfo("scrollable", output, "EventPanel"));
    }

    /**
     * Displays a dialog that allows the user  to select a tag to view events by
     * Displays a separate dialog if the user has no tags
     */
    public void viewEventsByTagChoice() {
        List<String> tags = eventManager.getAllTags(eventManager.getUserCalendarEvents(data.getEvents(), data.getCurrUser(), data.getCurrCalendar()));
        if (tags.size() == 0) {
            presenter.updateUI(new UIUpdateInfo("scrollable", new ArrayList<>(), "NoTagsPanel"));
        } else {
            presenter.updateUI(new UIUpdateInfo("scrollable", tags, "ViewByTagChoicePanel"));
        }
    }

    /**
     * Displays a dialog that has all the info of the events that contain the given tag
     *
     * @param tag the tag by which the user wants to categorize their view of events
     */
    public void viewEventsByTag(String tag) {
        List<Event> events = eventManager.getEventsByTag(tag, data);
        List<String> output = eventManager.getEventIDs(events);
//        List<String> formattedEvents = eventManager.formatEventsForSeries(events);
//        formattedEvents.add(0, tag);
        presenter.updateUI(new UIUpdateInfo("dialog", output, "EventListPanel"));
    }

    /**
     * Displays a dialog that shows events by a particular date threshold
     *
     * @param input relevant input
     */
    public void getEventsByDateThreshold(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input.get(0).equals("Error1")) {
            output.add("Error1");
            presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
        } else if (input.get(0).equals("Error2")) {
            output.add("Error2");
            presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
        } else {
            TimingFactory timingFactory = new TimingFactory();

            List<Integer> startDateParsed = parseDate(input.get(0));
            List<Integer> endDateParsed = parseDate(input.get(1));
            List<Integer> startTimeParsed = parseTime(input.get(2));
            List<Integer> endTimeParsed = parseTime(input.get(3));
            Timing timing = timingFactory.createTiming(startDateParsed.get(2), startDateParsed.get(1), startDateParsed.get(0),
                    startTimeParsed.get(0), startTimeParsed.get(1), endDateParsed.get(2), endDateParsed.get(1), endDateParsed.get(0),
                    endTimeParsed.get(0), endTimeParsed.get(1));
            if (timing == null) {
                output.add("Error2");
                presenter.updateUI(new UIUpdateInfo("dialog", output, "DateThresholdPanel"));
            } else {
                output = eventManager.getEventIDsOfThreshold(data, timing);
                presenter.updateUI(new UIUpdateInfo("dialog", output, "EventListPanel"));
            }
        }
    }

    /**
     * Displays a dialog that allows the user to view events by a specific name
     */
    public void viewEventByNameChoicePanel() {
        presenter.updateUI(new UIUpdateInfo("scrollable", Collections.singletonList(""), "ViewByENameChoicePanel"));
    }

    /**
     * Displays a panel that allows the user to inspect events with a particular name
     * @param eventName the name selected by the user
     */
    public void viewEventByNamePanel(String eventName) {
        List<String> ids = eventManager.getEventsCurrUserByName(data, eventName);
        if (ids.size() == 0) {
            presenter.updateUI(new UIUpdateInfo("scrollable", Collections.singletonList("No events were found with that name."), "ViewByENameChoicePanel"));
        } else {
            presenter.updateUI(new UIUpdateInfo("scrollable", ids, "EventListPanel"));
        }

    }
}
