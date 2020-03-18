package com.group_0225;

import com.group_0225.views.UIViews;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 * Class representing the controller for the program
 * @author Omar Shalash
 */

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class handles the logic and business rules of the Calendar
 */
public class Controller implements Observer {
    private SeriesManager sm;
    private AlertManager alertManager;
    private MemoManager memoManager;
    private List<List<String>> notifications = new ArrayList<>();
    private DatabaseManager databaseManager;
    private EventManager eventManager;
    private TimingFactory timingFactory;
    private Presenter presenter;
    private User currUser;
    private Event currEvent;


    /**
     * Creates an instance of Controller
     */
    public Controller() {
        readFromDatabase("database.txt");
        timingFactory = new TimingFactory();
        presenter = new Presenter();
        eventManager = new EventManager();
        memoManager = new MemoManager();
        sm = new SeriesManager();
    }

    /**
     * Runs the calendar (the home screen)
     */
    public void START() {
        boolean exit = false;
        List<String> input;
        while (!exit) {
            input = presenter.displayView(UIViews.startup, null);
            switch (Integer.parseInt(input.get(0))) {
                case 1:
                    userLogin();

                    break;
                case 2:
                    input = presenter.displayView(UIViews.createUser, null);
                    databaseManager.add(new User(input.get(0), input.get(1)));
                    writeIntoFile("database.txt");
                    break;
                case 3:
                    writeIntoFile("database.txt");
                    exit = true;
                    break;
            }

        }

        System.exit(1);
    }

    /**Let the user know that they have new notifications
     *
     * @param obs AlertManager
     * @param o list of alerts to display
     */
    @Override
    public void update(Observable obs, Object o){
        notifications.addAll((List<List<String>>)o);
        if(!notifications.isEmpty()) {
            presenter.displayView(UIViews.alertView, Arrays.asList("You have new notifications"));
        }
    }


    /**
     * Display the user's notifications
     */
    public void displayNotifications(){
        if (notifications.isEmpty()) {
            notifications.add(Arrays.asList("No new notifications."));


            for (List<String> s : notifications) {
                presenter.displayView(UIViews.alertView, s);
            }
            notifications.clear();
        }
        else {
            for (List<String> s : notifications) {
                presenter.displayView(UIViews.alertView, s);
            }
            notifications.clear();
        }
    }

    private void userLogin() {
        List<String> input = presenter.displayView(UIViews.loginView, null);
        User temp = databaseManager.findUser(input.get(0));
        if (temp != null && temp.validatePassword(input.get(1))) {
            currUser = temp;
            mainMenu();
        } else {
            input = presenter.displayView(UIViews.userDNE, null);
            if (Integer.parseInt(input.get(0)) == 1) {
                userLogin();
            }
        }
    }

    private void mainMenu() {
        boolean go = true;
        while (go) {
            eventManager.updateStatus(currUser);
            alertManager = new AlertManager(currUser.getEvents());
            alertManager.addObserver(this);
            notifications.addAll(alertManager.checkNewAlerts());
            alertManager.keepChecking();
            currentAlerts();
            List<String> mainMenuInput = presenter.displayView(UIViews.mainMenu, null);
            switch (Integer.parseInt(mainMenuInput.get(0))) {
                case 1:
                    displayNotifications();
                    break;
                case 2:
                    checkUpcomingAlerts();
                    break;
                case 3:
                    createMemo();
                    break;
                case 4:
                    createEvent();
                    break;
                case 5:
                    seriesMenu();
                    break;
                case 6:
                    displayEventsFilteredBy();
                    break;
                case 7:
                    go = false;
                    alertManager.stopTimer();
                    START();

                    break;
            }
        }
    }

    private void displayEventsFilteredBy() {
        boolean go = true;
        while (go) {
            eventManager.updateStatus(currUser);
            List<String> input = presenter.displayView(UIViews.displayEventBy, null);
            switch (Integer.parseInt(input.get(0))) {
                case 1:
                    eventsByStatus(1);
                    break;
                case 2:
                    eventsByStatus(2);
                    break;
                case 3:
                    eventsByStatus(3);
                    break;
                case 4:
                    eventByThreshold();
                    break;
                case 5:
                    memo();
                    break;
                case 6:
                    eventByTag(presenter.displayView(UIViews.CreateTag, null).get(0));
                    break;
                case 7:
                    eventByName(presenter.displayView(UIViews.SearchEvents, new ArrayList<>()).get(0));
                    break;
                case 8:
                    eventBySeriesName(presenter.displayView(UIViews.eventBySeriesName, null).get(0));
                    break;
                case 9:
                    go = false;
                    break;
            }
        }
    }

    private void eventByThreshold() {
        List<String> input = presenter.displayView(UIViews.dateThreshold, null);
        Timing date1 = timingFactory.createTiming(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)),
                Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)));
        ;

        Timing date2 = timingFactory.createTiming(Integer.parseInt(input.get(5)), Integer.parseInt(input.get(6)),
                Integer.parseInt(input.get(7)), Integer.parseInt(input.get(8)), Integer.parseInt(input.get(9)));

        List<Event> events = eventManager.getEventsBetween(currUser, date1, date2);
        displayEventsAfterFiltring(events);
    }

    private void memo() {
        List<String> strings = memoManager.DisplayAllMemos(currUser.getMemos());
        List<String> input = presenter.displayView(UIViews.memoMenu, strings);
        if (input.size() > 0) {
            List<Event> events = memoManager.FilterByMemoId(currUser.getEvents(), Integer.parseInt(input.get(0)));
            displayEventsAfterFiltring(events);
        }

    }

    private void displayEventsAfterFiltring(List<Event> events){
        List<String> outputs = new ArrayList<String>();
        List<String> input = new ArrayList<String>();
        for(Event event: events){
            outputs.addAll(eventManager.getDetailedEvent(event, alertManager));
        }
        input = presenter.displayView(UIViews.eventsInfoWithNumbers, outputs);
        int choice;
        try{
            choice = Integer.valueOf(input.get(0));
            if(choice > events.size() || choice <= 0)
                throw new NumberFormatException();
        }
        catch (Exception  e){
            return;
        }
        eventManipulation(events.get(Integer.parseInt(input.get(0)) - 1));
    }

    private void eventByTag(String tag) {
        List<Event> events = eventManager.getEventsByTag(currUser, tag);
        printDetailedEvents(events);
    }

    private void eventByName(String name) {
        List<Event> events = eventManager.searchEventsByName(currUser, name);
        printDetailedEvents(events);
    }

    private void eventBySeriesName(String seriesName) {
        List<Event> events = eventManager.getEventsBySeriesName(currUser, seriesName);
        printDetailedEvents(events);
    }

    private void eventsByStatus(int type) {
        ArrayList<Event> events;
        if (type == 1)
            events = eventManager.getCurrentEvents(currUser);
        else if (type == 2)
            events = eventManager.getPastEvents(currUser);
        else {
            events = eventManager.getUpcomingEvents(currUser);
        }
        displayEventsAfterFiltring(events);
    }

    private void eventManipulation(Event e) {
        boolean go = true;
        while (go) {
            eventManager.updateStatus(currUser);
            List<String> input = presenter.displayView(UIViews.EventManipulation, null);
            switch (Integer.parseInt(input.get(0))) {
                case 1:
                    input = presenter.displayView(UIViews.ChangeName, null);
                    eventManager.changeEventName(currUser, e, input.get(0));
                    break;
                case 2:
                    createOneAlert(e);
                    break;
                case 3:
                    createRecurringAlert(e);
                    break;
                case 4:
                    associateMemoWithEvent(e);
                case 5:
                    eventManager.addTag(e, presenter.displayView(UIViews.CreateTag, null).get(0));
                case 6:
                    go = false;
                    break;
            }
        }
    }

    private void associateMemoWithEvent(Event e) {
        List<String> outputs = memoManager.DisplayAllMemos(currUser.getMemos());
        List<String> inputs = presenter.displayView(UIViews.listMemos, outputs);
        Integer choice = Integer.parseInt(inputs.get(0));
        try {
            currUser.getMemos().get(choice);
            e.addMemoID(choice);
        } catch (Exception ex) {
            presenter.displayView(UIViews.error, null);
            associateMemoWithEvent(e);
        }
    }


    private void readFromDatabase(String filePath) {
        try {
            databaseManager = new DatabaseManager(filePath);
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    /**
     * Writes onto the file
     *
     * @param filePath the String where the file is stored
     */
    public void writeIntoFile(String filePath) {
        try {
            databaseManager.saveToFile(filePath);
        } catch (IOException ex) {
        }
    }

    /**
     * Displays the current alerts
     */
    public void currentAlerts(){
        List<List<String>> alerts = alertManager.checkNewAlerts();

        if(!alerts.isEmpty()){
            notifications.addAll(alerts);
            displayNotifications();
        }

    }

    private String formatEventInfo(Event e){
        StringBuilder s = new StringBuilder();
        s.append("Name: ").append(e.getEventName()).append("\n");
        s.append("Series Name: ").append(e.getSeriesName()).append("\n");
        s.append(parseDateAsString(e.getTime())).append("\n");
        s.append("Tags: ").append(parseTagsAsString(e.getTags())).append("\n\n");
        return s.toString();
    }

    private List<String> listEvents(User u) {
        List<String> str = new ArrayList<>();
        for (Event e : u.getEvents()) {
            str.add(formatEventInfo(e));
        }
        return str;
    }

    private void seriesMenu() {
        List<String> input = presenter.displayView(UIViews.seriesMenu, null);

        int i = Integer.parseInt(input.get(0));
        if (i == 1) {
            createSeriesFromEvents();
        } else if (i == 2) {
            createSeriesFromScratch();
        }
        else{
            mainMenu();
        }


    }

    private void createSeriesFromEvents() {
        if (currUser.getEvents().size() < 2) {
            System.out.println("You do not have enough existing events to create a series. \nInstead, you can make a series from scratch.");
            return;
        }
        List<String> input = presenter.displayView(UIViews.createSeriesEvents, listEvents(currUser));
        String[] eventSelectionsArray = input.get(0).split(",");
        List<String> eventSelections = Arrays.asList(eventSelectionsArray);
        boolean flawless = true;
        int i;
        List<Integer> indices = new ArrayList<>();
        if (parsable(eventSelections)) {
            for (String s : eventSelections) {
                i = Integer.parseInt(s);
                indices.add(i);
                flawless = flawless && (i >= 0 && i < currUser.getEvents().size());
            }
            if (flawless) {
                sm.createSeries(input.get(1), currUser, indices);
                writeIntoFile("database.txt");
                return;
            }
        }
        System.out.println("Your event selection was invalid. Please try again.");
        createSeriesFromEvents();
    }

    private void createSeriesFromScratch() {
        List<String> input = presenter.displayView(UIViews.createSeriesScratch, null);
        List<String> sub = input.subList(1, input.size());
        if (parsable(sub)) {
            List<Integer> numInput = getIntegerList(sub);
            List<Integer> date = numInput.subList(0, 5);
            int freq = numInput.get(5);
            List<Integer> duration = numInput.subList(6, 9);
            int nE = numInput.get(9);
            List<Integer> freqDur = numInput.subList(5, 9);
            if (verifyStartDate(date) && verifyFrequency(freq) && verifyDuration(duration)
                    && verifyNumEvents(nE) && verifyDurationLTFreq(freqDur)) {
                sm.createSeries(currUser, input.get(0), duration, date, freq, nE, eventManager);
                writeIntoFile("database.txt");
                return;
            }
        }
        System.out.println("Some of your input was invalid. Please try again.");
        createSeriesFromScratch();

    }

    private void checkUpcomingAlerts() {
        Presenter p = new Presenter();
        List<List<String>> alertList = alertManager.checkUpcomingAlerts();
        if (alertList.isEmpty()) {
            alertList.add(Arrays.asList("No upcoming alerts."));
        }
        for (List<String> s : alertList) {
            p.displayView(UIViews.alertView, s);
        }
    }

    private boolean parsable(List<String> lst) {
        int i;
        for (String s : lst) {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyNumEvents(int i) {
        return i >= 2;
    }
    private boolean verifyStartEnd(Timing t){
        return t.getStart().isBefore(t.getEnd());   }

    private boolean verifyFrequency(int i) {
        if (!(i>=1 && i<=5)){
            System.out.println("Your frequency selection was incorrect.");}
        return i >= 1 && i <= 5;
    }

    private boolean verifyDuration(List<Integer> lst) {
        if (!((0 <= lst.get(0)) && (6 >= lst.get(0)) && (lst.get(1) >= 0) && (lst.get(1) <= 23)
                && (lst.get(2) >= 0) && (lst.get(2) <= 59))){
            System.out.println("Your duration selection was incorrect.");
        }
        return (0 <= lst.get(0)) && (6 >= lst.get(0)) && (lst.get(1) >= 0) && (lst.get(1) <= 23)
                && (lst.get(2) >= 0) && (lst.get(2) <= 59);
    }

    private boolean verifyStartDate(List<Integer> lst) {
        if (!(lst.get(1) >= 1 && lst.get(1) <= 12 && lst.get(2) >= 1 && lst.get(2) <= 31
                && lst.get(3) >= 0 && lst.get(3) <= 23 && lst.get(4) >= 0 && lst.get(4) <= 59)){
            System.out.println("Your start date selection was incorrect.");
        }
        return lst.get(1) >= 1 && lst.get(1) <= 12 && lst.get(2) >= 1 && lst.get(2) <= 31
                && lst.get(3) >= 0 && lst.get(3) <= 23 && lst.get(4) >= 0 && lst.get(4) <= 59;
    }

    private boolean verifyDurationLTFreq(List<Integer> lst) {
        //lst contains 8 numbers (the selected duration appended to the selected frequency)
        boolean ret;
        switch (lst.get(0)) {
            case 1:
                ret =  lst.get(1) < 1 && lst.get(2) < 1;
                break;
            case 2:
                ret =  lst.get(1) < 1;
                break;
            default:
                ret =  true;
                break;
        }
        if (!ret) {
            System.out.println("The duration of each event is longer than the frequency.");
        }  return ret;
    }

    private List<Integer> getIntegerList(List<String> lst) {
        List<Integer> newLst = new ArrayList<>();
        int i;
        for (String s : lst) {
            i = Integer.parseInt(s);
            newLst.add(i);
        }
        return newLst;
    }

    private void createEvent() {
        List<String> input = presenter.displayView(UIViews.createEvent, null);
        List<Integer> dates = new ArrayList<Integer>();
        String eventName = input.get(0);
        if (parsable(input.subList(1, 10))) {
            dates = getIntegerList(input.subList(1, 11));
            Timing eventTiming = timingFactory.createTiming(dates.get(0), dates.get(1), dates.get(2), dates.get(3),dates.get(4),
                    dates.get(5), dates.get(6), dates.get(7), dates.get(8), dates.get(9));
            if (!verifyStartEnd(eventTiming)){
                System.out.println("Your start time is later than your end time. Please try again.");
                createEvent();
                return;
            }
            if (input.get(input.size() - 1).toLowerCase().equals("yes")) {
                Event e = eventManager.createEvent(currUser, eventName, eventTiming);
                alertManager.createNewAlert(e, timingFactory.createTiming(eventTiming.getStart()),
                        "The event " + e.getEventName() + " is starting.");
            } else {
                eventManager.createEvent(currUser, eventName, eventTiming);
            }
            writeIntoFile("database.txt");
        } else {
            System.out.println("===Try Again - Invalid Input===");
            createEvent();
        }
    }

    private String parseDateAsString(Timing t) {
        LocalDateTime start = t.getStart();
        LocalDateTime end = t.getEnd();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
        String startFormat = start.format(formatter);
        String endFormat = end.format(formatter);
        return "Start: " + startFormat + "\nEnd: " + endFormat;
    }

    private String parseTagsAsString(ArrayList<String> tags) {
        if (tags.size() == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (String tag : tags) {
            s.append(tag).append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

    private void createRecurringAlert(Event e) {
        Presenter p = new Presenter();
        List<String> inputs = presenter.displayView(UIViews.createRecurringAlertView, null);
        if (parsable(inputs.subList(1, 9))) {
            DurationFactory durationFactory = new DurationFactory();
            List<Integer> date = getIntegerList(inputs.subList(1, 6));
            Timing t = timingFactory.createTiming(date.get(0).intValue(), date.get(1).intValue(), date.get(2).intValue(), date.get(3).intValue(), date.get(4).intValue());
            List<Integer> dur = getIntegerList(inputs.subList(6, 9));
            Duration d = durationFactory.createDuration(dur.get(0).intValue(), dur.get(1).intValue(), dur.get(2).intValue());
            if (inputs.get(0).equals("null")) {
                alertManager.createNewAlert(e, t, d);
            } else {
                alertManager.createNewAlert(e, t, inputs.get(0), d);
            }
            return;
        }
        createRecurringAlert(e);
    }

    private void createOneAlert(Event e) {
        Presenter p = new Presenter();
        List<String> inputs = presenter.displayView(UIViews.createOneAlertView, null);
        if (parsable(inputs.subList(1, 6))) {
            List<Integer> date = getIntegerList(inputs.subList(1, 6));
            Timing t = timingFactory.createTiming(date.get(0).intValue(), date.get(1).intValue(), date.get(2).intValue(), date.get(3).intValue(), date.get(4).intValue());
            if (inputs.get(0).equals("null")) {
                alertManager.createNewAlert(e, t);
            } else {
                alertManager.createNewAlert(e, t, inputs.get(0));
            }
            return;
        }
        createOneAlert(e);
    }

    private void createMemo() {
        List<Event> allEvents = new ArrayList<>();
        allEvents.addAll(eventManager.getCurrentEvents(currUser));
        allEvents.addAll(eventManager.getPastEvents(currUser));
        allEvents.addAll(eventManager.getUpcomingEvents(currUser));
        List<String> listOfStrings = toListString(allEvents);
        List<String> memoMessage = presenter.displayView(UIViews.createMemo, null);
        List<String> indices = presenter.displayView(UIViews.memoEventPicking, listOfStrings);
        int id = memoManager.CreateMemo(currUser.getMemos(), memoMessage.get(0), allEvents);
        writeIntoFile("database.txt");
        for (String index : indices) {
            int currIndex = Integer.parseInt(index);
            allEvents.get(currIndex).addMemoID(id);
        }
    }

    private List<String> toListString(List<Event> listOfEvents) {
        List<String> listOfStrings = new ArrayList<>();
        for (Event event : listOfEvents) {
            listOfStrings.add(event.getEventName());
        }
        return listOfStrings;
    }

    private void printDetailedEvents(List<Event> listOfEvents) {
        for (Event e : listOfEvents) {
            printDetailedEvent(e);
        }
        if (listOfEvents.isEmpty()){
            //System.out.println("No events match this description.\n");
        }
    }

    private void printDetailedEvent(Event e) {
        presenter.displayView(UIViews.EventInfo, eventManager.getDetailedEvent(e, alertManager));
    }
}
