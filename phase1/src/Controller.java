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

    private User curr;
    private SeriesManager sm;

    private DatabaseManager databaseManager;
    private EventManager eventManager;
    private TimingFactory timingFactory;
    private Presenter presenter;


    /**
     * Creates an instance of Controller
     */
    public Controller(){
        timingFactory = new TimingFactory();
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
        Presenter p1 = new Presenter();

        List<String> input =  p1.displayView(UIViews.startup, null);
        switch (Integer.parseInt(input.get(0))){
            case 1: //MAIN MENU AT THE MOMENT

                input = p1.displayView(UIViews.mainMenu, Arrays.asList("No current alerts"));
                break;
            case 2:
                input =  p1.displayView(UIViews.createUser, null);
                break;
            case 3:
                System.exit(1);
                break;
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

    public List<String> formatEventInfo(Event e){
        List<String> lst = new ArrayList<String>();
        lst.add(e.getEventName());
        lst.add(e.getSeriesName());

        return lst;
    }

    public void createSeriesFromScratch() {
        Presenter p1 = new Presenter();
        List<String> lst = new ArrayList<>();
        lst.add("What is the frequency of events" +
                "in this series?\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly");
        List<String> input = p1.displayView(UIViews.createSeriesScratch, lst);
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
                sm.createSeries(curr,input.get(0),duration,date,freq, nE);
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
}
