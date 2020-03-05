import views.UIViews;

import java.io.IOException;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 * Class representing the controller for the program
 * @author Omar Shalash
 */

import java.util.ArrayList;
import java.util.List;


public class Controller {

    private User curr;
    private SeriesManager sm;

    private DatabaseManager databaseManager;
    private EventManager eventManager;
    private TimingFactory timingFactory;
    private Presenter presenter;

    private List<User> userList = new ArrayList<>(); //TODO REMOVE THIS


    public Controller(){
        timingFactory = new TimingFactory();
        userList.add(new User("Daniel", "Daniel"));
        eventManager = new EventManager();
        eventManager.createEvent(userList.get(0), "Birthday", timingFactory.createTiming(2020, "January",2,2, 2));
    }



    public String createSeriesFromScratch(String seriesName, String duration, String date, int fSelection, int neSelection) {
        //interpret date as localtime and then use between to find elapsed time between localtime and midnight, convert to duration
        if (neSelection < 2) {
            return "Series must have at least 2 events.";
        }
        LocalTime lt = LocalTime.parse(duration);
        Duration d = Duration.between(LocalTime.MIN, lt);
        LocalDateTime ldt = LocalDateTime.parse(date);
        sm.createSeries(curr, seriesName, d, ldt, fSelection, neSelection);
        return "Series " + seriesName + " created.";
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
            case 1:

            case 2:
                input =  p1.displayView(UIViews.createUser, null);
                userList.add(new User(input.get(0), input.get(1)));
                System.out.println(userList.get(0).getUsername());
            case 3:
                System.exit(1);
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
}
