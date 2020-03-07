import views.UIViews;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 * Class representing the controller for the program
 * @author Omar Shalash
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles the logic and business rules of the Calendar
 */
public class Controller {

    private SeriesManager sm;

    private DatabaseManager databaseManager;
    private EventManager eventManager;
    private TimingFactory timingFactory;
    private Presenter presenter;
    private User currUser;


    /**
     * Creates an instance of Controller
     */
    public Controller(){
        readFromDatabase("database.txt");
        timingFactory = new TimingFactory();
        presenter = new Presenter();
        eventManager = new EventManager();
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
        while (true) {
            List<String> mainMenuInput = presenter.displayView(UIViews.mainMenu, null);
            switch (Integer.parseInt(mainMenuInput.get(0))) {
                case 1:
                case 2:
                case 3:
                    createEvent();
                    break;
                case 4:
                case 5:
                case 6:
                    displayEventsFilteredBy();
                    break;
                case 7:
                    START();
            }
        }
    }

    private void displayEventsFilteredBy() {
        while (true) {
            List<String> input = presenter.displayView(UIViews.displayEventBy, null);
            switch (Integer.parseInt(input.get(0))) {
                case 1:
                case 2:
                case 3:
                    upcomingEvents();
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    mainMenu();
            }
        }


    }

    private void upcomingEvents(){
        ArrayList<Event> upcomingEvents = eventManager.getUpcomingEvents(currUser);
        List<String > temp = new ArrayList<>();
        for (Event event : upcomingEvents){
            temp.add("Event Name: " + event.getEventName());
            temp.add("Start Data and Time: " + event.getStartTimeString());
            temp.add("End Date and Time: " + event.getEndTimeString());
        }
        presenter.displayView(UIViews.eventInfo, temp);
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

    public List<String> formatEventInfo(Event e){
        List<String> lst = new ArrayList<String>();
        lst.add(e.getEventName());
        lst.add(e.getSeriesName());

        return lst;
    }
    public void createSeriesFromEvents(){
        Presenter p = new Presenter();
    }
    public void createSeriesFromScratch() {
        Presenter p1 = new Presenter();
        List<String> input = p1.displayView(UIViews.createSeriesScratch, null);
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
                sm = new SeriesManager();
                sm.createSeries(currUser,input.get(0),duration,date,freq, nE);
                return;
            }
        }createSeriesFromScratch();

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
        String eventName = input.get(0);
        List<Integer> times = parseTiming(input.get(1), input.get(2));
        Timing eventTiming = timingFactory.createTiming(times.get(0), times.get(1), times.get(2), times.get(3),
                times.get(4), times.get(5), times.get(6), times.get(7), times.get(8), times.get(9));
        eventManager.createEvent(currUser, eventName, eventTiming);
        writeIntoFile("database.txt");
    }

    /**
     * Helper method that parses a formatted string date and returns a list of the values
     * @param date1 The first date
     * @param date2 The second date
     * @return List of values corresponding to those dates
     */
    private List<Integer> parseTiming(String date1, String date2){
        List<Integer> times = new ArrayList<>();
        int year1 = Integer.parseInt(date1.substring(0, 4));
        int month1 = Integer.parseInt(date1.substring(5, 7));
        int day1 = Integer.parseInt(date1.substring(8, 10));
        int hour1 = Integer.parseInt(date1.substring(11, 13));
        int minute1 = Integer.parseInt(date1.substring(14, 16));
        int year2 = Integer.parseInt(date2.substring(0, 4));
        int month2 = Integer.parseInt(date2.substring(5, 7));
        int day2 = Integer.parseInt(date2.substring(8, 10));
        int hour2 = Integer.parseInt(date2.substring(11, 13));
        int minute2 = Integer.parseInt(date2.substring(14, 16));
        times.add(year1);
        times.add(month1);
        times.add(day1);
        times.add(hour1);
        times.add(minute1);
        times.add(year2);
        times.add(month2);
        times.add(day2);
        times.add(hour2);
        times.add(minute2);
        return times;
    }
}
