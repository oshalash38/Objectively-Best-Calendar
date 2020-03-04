import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
public class Controller {
    /**
     * Class representing the controller for the program
     * @author Omar Shalash
     */
    private User curr;
    private SeriesManager sm;



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

    public void START(){
        Presenter p1 = new Presenter();
        switch (p1.getStartUpPageInput()){
            case 1:

            case 2:

            case 3:
                System.exit(1);
        }
    }
}
