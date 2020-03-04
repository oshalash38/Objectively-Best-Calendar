import java.io.IOException;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
/**
 * Class representing the controller for the program
 * @author Omar Shalash
 */
public class Controller {

    private User curr;
    private SeriesManager sm;
    private DatabaseManager databaseManager;


    public String createSeriesFromScratch(String seriesName, String duration, String date, int fSelection, int neSelection){
        //interpret date as localtime and then use between to find elapsed time between localtime and midnight, convert to duration
        if (neSelection < 2){
            return "Series must have at least 2 events.";
        }
        LocalTime lt = LocalTime.parse(duration);
        Duration d = Duration.between(LocalTime.MIN, lt);
        LocalDateTime ldt = LocalDateTime.parse(date);
        sm.createSeries(curr, seriesName, d, ldt, fSelection, neSelection);
        return "Series " + seriesName + " created.";
    }
    public String createSeriesFromEvents(String seriesName){

        return "";
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
}
