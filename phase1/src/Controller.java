import views.UIViews;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 * Class representing the controller for the program
 * @author Omar Shalash
 */

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
    public Controller(){
        readFromDatabase("database.txt");
        timingFactory = new TimingFactory();
        presenter = new Presenter();
        eventManager = new EventManager();
        memoManager = new MemoManager();
        sm = new SeriesManager();
    }




//    public String createSeriesFromEvents(User u, String seriesName) {
//        String s;
//        String[] sSplit;
//        ArrayList<Integer> indices;
//        int i;
//        boolean flawless;
//        do {
//            s = presenter.getEventsForSeries(u);
//            sSplit = s.split(",");
//            indices = new ArrayList<>();
//            flawless = true;
//            for (String selection : sSplit) {
//                try {
//                    i = Integer.parseInt(selection);
//                    if (i < 0 || i > u.getEvents().size() - 1) {
//                        flawless = false;
//                        System.out.println(selection + " is out of bounds. Enter your selections again.");
//                        break;
//                    }
//                    indices.add(i);
//                } catch (NumberFormatException x) {
//                    flawless = false;
//                    System.out.println(selection + " is not a valid input type. Enter your selections again.");
//                    break;
//                }
//            }
//        } while (!flawless);
//        sm.createSeries(seriesName, u, indices);
//        return "Series " + seriesName + " created.";
//    }

    public void START(){
        boolean exit = false;
        List<String> input;
        while (!exit) {
            input =  presenter.displayView(UIViews.startup, null);
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
                    exit = true;
                    break;
            }

        }

        System.exit(1);
    }

    public void update(Observable obs, Object o){
        notifications.addAll((List<List<String>>)o);
        if (notifications.size()!=0){ System.out.println("You have new notifications.");}

    }

    public void displayNotifications(){

        if(notifications.size()>0) {
            for (List<String> s : notifications) {
                presenter.displayView(UIViews.alertView, s);
            }
            notifications.clear();
        }
        else {
            System.out.println("No Alerts :)");
        }
    }

    private void userLogin(){
        List<String> input = presenter.displayView(UIViews.loginView, null);
        User temp = databaseManager.findUser(input.get(0));
        if(temp != null && temp.validatePassword(input.get(1))){
            currUser = temp;
            mainMenu();
        }
        else{
            input = presenter.displayView(UIViews.userDNE, null);
            if(Integer.parseInt(input.get(0)) == 1){
                userLogin();
            }
        }
    }

    private void mainMenu(){
        boolean go = true;
        while (go) {
            alertManager = new AlertManager(currUser.getEvents());
            alertManager.addObserver(this);
            notifications.addAll(alertManager.checkNewAlerts());
            alertManager.keepChecking();
            currentAlerts();
            List<String> mainMenuInput = presenter.displayView(UIViews.mainMenu, null);
            switch (Integer.parseInt(mainMenuInput.get(0))) {
                case 1:
                    checkUpcomingAlerts();
                    break;
                case 2:
                    createMemo();
                    break;
                case 3:
                    createEvent();
                    break;
                case 4:
                case 5:
                    displayEventsFilteredBy();
                    break;
                case 6:
                    go = false;
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
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    go = false;
                    break;
            }
        }


    }

    private void eventsByStatus(int type){
        ArrayList<Event> upcomingEvents;
        if(type == 1)
            upcomingEvents = eventManager.getCurrentEvents(currUser);
        else if(type == 2)
            upcomingEvents = eventManager.getPastEvents(currUser);
        else{
            upcomingEvents = eventManager.getUpcomingEvents(currUser);
        }
        List<String > temp = new ArrayList<>();
        for (Event event : upcomingEvents){
            temp.add("Event Name: " + event.getEventName());
            temp.add("Start Data and Time: " + event.getStartTimeString());
            temp.add("End Date and Time: " + event.getEndTimeString());
        }
            List<String> input = presenter.displayView(UIViews.eventInfo, temp);
        if (input.size() > 0) {
            if (Integer.parseInt(input.get(0)) == 1) {//User want to edit one of the events displayed
            }
        }
    }


    public void readFromDatabase(String filePath){
        try {
            databaseManager = new DatabaseManager(filePath);
        }
        catch (IOException ex){
            System.out.println("Could not load database. Please try again later");}
        catch (ClassNotFoundException ex) {
            System.out.println("Database may be corrupted. Could not load database.");
        }
    }

    public void writeIntoFile(String filePath){
        try{
            databaseManager.saveToFile(filePath);
        }
        catch(IOException ex){
            System.out.println("Failed to write into database. Please try again later");
        }
    }

    public void currentAlerts(){

        notifications.addAll(alertManager.checkNewAlerts());
        displayNotifications();
    }
    private String formatEventInfo(Event e){
        StringBuilder s = new StringBuilder();
        s.append("Name: ").append(e.getEventName()).append("\n");
        s.append("Series Name: ").append(e.getSeriesName()).append("\n");
        s.append(parseDateAsString(e.getTime())).append("\n");
        s.append("Tags: ").append(parseTagsAsString(e.getTags())).append("\n\n");
        return s.toString();
    }
    private List<String> listEvents(User u){
        List<String> str = new ArrayList<>();
        for (Event e: u.getEvents()){
            str.add(formatEventInfo(e));
        }
        return str;
    }
    public void createSeriesFromEvents(){
        List<String> input = presenter.displayView(UIViews.listEvents, listEvents(currUser));
        String[] eventSelectionsArray = input.get(0).split(",");
        List<String> eventSelections = Arrays.asList(eventSelectionsArray);
        boolean flawless = true; int i; List<Integer> indices = new ArrayList<>();
        if (parseable(eventSelections)){
            for (String s: eventSelections){
                i = Integer.parseInt(s);
                indices.add(i);
                flawless = flawless && (i>=0 && i<=currUser.getEvents().size());
            }
            if (flawless){
                sm.createSeries(input.get(1), currUser, indices);
            }
        }
        System.out.println("Your event selection was invalid. Please try again.");
        createSeriesFromEvents();
    }
    public void createSeriesFromScratch() {
        List<String> input = presenter.displayView(UIViews.createSeriesScratch, null);
        List<String> sub = input.subList(1, input.size());
        if (parseable(sub)) {
            List<Integer> numInput = getIntegerList(sub);
            List<Integer> date = numInput.subList(0, 5);
            int freq = numInput.get(5);
            List<Integer> duration = numInput.subList(6, 9);
            int nE = numInput.get(9);
            List<Integer> freqDur = numInput.subList(5, 9);
            if (verifyStartDate(date) && verifyFrequency(freq) && verifyDuration(duration)
                    && verifyNumEvents(nE) && verifyDurationLTFreq(freqDur)) {
                sm.createSeries(currUser,input.get(0),duration,date,freq, nE);
                writeIntoFile("database.txt");
                return;
            }
        }
        System.out.println("Some of your input was invalid. Please try again.");createSeriesFromScratch();

    }

    public void checkUpcomingAlerts(){
        Presenter p = new Presenter();
        List<List<String>> alertList = alertManager.checkUpcomingAlerts();
        if (alertList.isEmpty()){
            alertList.add(Arrays.asList("No upcoming alerts."));
        }
        for (List<String> s: alertList){
            p.displayView(UIViews.alertView, s);
        }

    }

    private boolean parseable(List<String> lst){
        int i;
        for (String s: lst){
            try{
                i = Integer.parseInt(s);
            }catch(NumberFormatException e){
                return false;
            }
        }return true;
    }

    /** Verify valid input for number of events
     *
     * @param i number of events the user chose
     * @return true iff the user chose more than one event to be in a series
     */
    private boolean verifyNumEvents(int i){
        return i >= 2;
    }

    /**Verify valid input for frequency
     *
     * @param i how often the events in this series will begin
     * @return true iff the user chose one of hourly, daily, weekly, monthly, or yearly
     */
    private boolean verifyFrequency(int i){
        return i>=1 && i<=5;
    }

    /**Verify valid input for duration
     *
     * @param lst the List<Integer> of inputs the user gave for duration of each event in the series
     * @return true iff the selections were valid
     */
    private boolean verifyDuration(List<Integer> lst){
        return (0 <= lst.get(0)) && (6 >= lst.get(0)) && (lst.get(1) >= 0) && (lst.get(1) <= 23)
                && (lst.get(2) >= 0) && (lst.get(2) <= 59);
    }

    /**Verify valid input for the start date
     *
     * @param lst the List<Integer> of inputs the user gave for the start date of the series
     * @return true iff the user's inputs add up to a valid start date and time
     */
    private boolean verifyStartDate(List<Integer> lst){
        return lst.get(1) >= 1 && lst.get(1) <= 12 && lst.get(2) >= 1 && lst.get(2) <= 31
                && lst.get(3) >= 0 && lst.get(3) <= 23 && lst.get(4) >= 0 && lst.get(4) <= 59;
    }

    /**Verify valid input for duration with respect to frequency
     *
     * @param lst the user's input for duration and frequency
     * @return true iff the duration does not extend past the frequency
     */
    private boolean verifyDurationLTFreq(List<Integer> lst){
        //lst contains 8 numbers (the selected duration appended to the selected frequency)
        switch(lst.get(0)){
            case 1:
                return lst.get(1)<1 && lst.get(2)<1;
            case 2:
                return lst.get(1)<1;
            default:
                return true;
        }
    }

    /**Get an integer list from a list of strings (assume all elements can be parsed)
     *
     * @param lst the original list of strings
     * @return a list of integers representing the old list of strings
     */
    private List<Integer> getIntegerList(List<String> lst){
        List<Integer> newLst = new ArrayList<>();
        int i;
        for (String s: lst){
            i = Integer.parseInt(s);
            newLst.add(i);
        }
        return newLst;
    }

    /**
     * Creates a new event.
     */
    private void createEvent(){
        List<String> input = presenter.displayView(UIViews.createEvent, null);
        List<Integer> dates = new ArrayList<Integer>();
        String eventName = input.get(0);
        if(parseable(input.subList(1,10))){
            dates = getIntegerList(input.subList(1,11));
            Timing eventTiming = timingFactory.createTiming(dates.get(0), dates.get(1), dates.get(2),dates.get(3),
                    dates.get(4),dates.get(5),dates.get(6),dates.get(7),dates.get(8),dates.get(9));

            if(input.get(input.size()-1).toLowerCase().equals("yes")){
                Event e = eventManager.createEvent(currUser, eventName, eventTiming);
                alertManager.createNewAlert(e, timingFactory.createTiming(eventTiming.getStart()),
                        "The event " + e.getEventName() + " is starting.");
            }
            else{
                eventManager.createEvent(currUser, eventName, eventTiming);
            }
            writeIntoFile("database.txt");
        }
        else{
            createEvent();
        }
    }

    /**Parse a Timing object as a String with the start and end date
     *
     * @param t the Timing object passing the date
     * @return a String representing the start and end date (on different lines)
     */
    private String parseDateAsString(Timing t){
        LocalDateTime start = t.getStart();
        LocalDateTime end = t.getEnd();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String startFormat = dateFormat.format(start);
        String endFormat = dateFormat.format(end);
        return "Start: " + startFormat + "\nEnd: " + endFormat;
    }
    private String parseTagsAsString(ArrayList<String> tags){
        if (tags.size() == 0){return "";}
        StringBuilder s = new StringBuilder();
        for (String tag: tags){
            s.append(tag).append(", ");
        }
        return s.substring(0, s.length()-2);
    }

    private void createRecurringAlert(Event e){
        Presenter p = new Presenter();
        List<String> inputs = presenter.displayView(UIViews.createRecurringAlertView, null);
        if (parseable(inputs.subList(1, 9))) {
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
            return ;
        }
        System.out.println("Invalid inputs please try again.");
        createRecurringAlert(e);
    }

    private void createOneAlert(Event e){
        Presenter p = new Presenter();
        List<String> inputs = presenter.displayView(UIViews.createOneAlertView, null);
        if (parseable(inputs.subList(1, 6))) {
            List<Integer> date = getIntegerList(inputs.subList(1, 6));
            Timing t = timingFactory.createTiming(date.get(0).intValue(), date.get(1).intValue(), date.get(2).intValue(), date.get(3).intValue(), date.get(4).intValue());
            if (inputs.get(0).equals("null")) {
                alertManager.createNewAlert(e, t);
            } else {
                alertManager.createNewAlert(e, t, inputs.get(0));
            }
            return ;
        }
        System.out.println("Invalid inputs please try again.");
        createOneAlert(e);
    }

    private void createMemo(){

        List<String> inputs = presenter.displayView(UIViews.createMemo, null);
        memoManager.CreateMemo(currUser.getMemos(), inputs.get(0));
    }
}
