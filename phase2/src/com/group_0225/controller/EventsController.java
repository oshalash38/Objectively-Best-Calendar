package com.group_0225.controller;

import com.group_0225.*;
import com.group_0225.manager.CalendarData;
import com.group_0225.views.UIViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventsController extends CalendarController{
    private EventManager eventManager = new EventManager();
    private User currUser;
    private List<Event> events = new ArrayList<>();
    private TimingFactory timingFactory;
    private MemoManager memoManager;
    public EventsController(CalendarData data, UIPresenter presenter, User currUser) {

        super(data, presenter);
        this.currUser = currUser;
        timingFactory = new TimingFactory();
        memoManager = new MemoManager();
        getEvents();

    }

    public void updateUser(User newUser){this.currUser = newUser;}

    private void getEvents(){
        List<Integer> eventID = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> calendar: currUser.getMap().entrySet()) {

            eventID.addAll(calendar.getValue());
        }
        for (Integer id: eventID){
            events.add(data.getEvents().get(id));
        }
    }

    private void printDetailedEvents(List<Event> events) {

    }
    private void eventByTag(String tag) {
        List<Event> events = eventManager.getEventsByTag(currUser, tag, this.events);
        printDetailedEvents(events);
    }

    private void eventByName(String name) {
        List<Event> events = eventManager.searchEventsByName(currUser, name, this.events);
        printDetailedEvents(events);
    }

    private void eventBySeriesName(String seriesName) {
        List<Event> events = eventManager.getEventsBySeriesName(currUser, seriesName, this.events);
        printDetailedEvents(events);
    }

    private void eventsByStatus(int type) {
        ArrayList<Event> retEvents;
        eventManager.updateStatus(currUser, this.events);
        if (type == 1)
            retEvents = eventManager.getCurrentEvents(currUser, events);
        else if (type == 2)
            retEvents = eventManager.getPastEvents(currUser, events);
        else {
            retEvents = eventManager.getUpcomingEvents(currUser, events);
        }
        printDetailedEvents(retEvents);
    }
    private void eventByThreshold(List<String> input) {
        Timing date1 = timingFactory.createTiming(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)),
                Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)));


        Timing date2 = timingFactory.createTiming(Integer.parseInt(input.get(5)), Integer.parseInt(input.get(6)),
                Integer.parseInt(input.get(7)), Integer.parseInt(input.get(8)), Integer.parseInt(input.get(9)));

        List<Event> events = eventManager.getEventsBetween(currUser, date1, date2, this.events);
        //TODO: decide return
       // displayEventsAfterFiltring(events);
    }

    private void eventByMemo(Integer memoID) {
        List<Event> events = memoManager.FilterByMemoId(this.events , memoID);

        //displayEventsAfterFiltring(events);

    }
}

