import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    /**
     * Class representing the controller for the program
     * @author Omar Shalash
     */
    private User curr;
    private SeriesManager sm;
    private Presenter presenter;


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

    public String createSeriesFromEvents(User u, String seriesName) {
        String s;
        String[] sSplit;
        ArrayList<Integer> indices;
        int i;
        boolean flawless;
        do {
            s = presenter.getEventsForSeries(u);
            sSplit = s.split(",");
            indices = new ArrayList<>();
            flawless = true;
            for (String selection : sSplit) {
                try {
                    i = Integer.parseInt(selection);
                    if (i < 0 || i > u.getEvents().size() - 1) {
                        flawless = false;
                        System.out.println(selection + " is out of bounds. Enter your selections again.");
                        break;
                    }
                    indices.add(i);
                } catch (NumberFormatException x) {
                    flawless = false;
                    System.out.println(selection + " is not a valid input type. Enter your selections again.");
                    break;
                }
            }
        } while (!flawless);
        sm.createSeries(seriesName, u, indices);
        return "Series " + seriesName + " created.";
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
