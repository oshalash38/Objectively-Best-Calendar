import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Methods for creating a series
 * @author Peter Sellars
 */
public class SeriesManager {
    /**
     * Creates a list of new Events that form a series
     *
     * @param seriesName the name of the new series
     * @param dur        the duration of each event in the series
     * @param start      the beginning of the series
     * @return the list of events forming this series
     */
    private TimingFactory tf = new TimingFactory();
    private DurationFactory df = new DurationFactory();
    public void createSeries(User u, String seriesName, List<Integer> dur, List<Integer> start, int fSelection, int neSelection) {
        ArrayList<Event> lst = new ArrayList<>();
        Timing temp = tf.createTiming(start.get(0), start.get(1), start.get(2), start.get(3), start.get(4));
        Duration length = df.createDuration(dur.get(0), dur.get(1), dur.get(2));
        Event e;
        EventManager em = new EventManager();
        for (int i = 0; i< neSelection; i++){
            em.createEvent(u,"",temp, seriesName);
        }
    }

    /**
     * Changes the seriesName of a bunch of events to link them together as a series
     *
     * @param seriesName the name of the new series
     * @param u     the user that is making this series
     * @param indices the list of events that the user has requested to make into a series
     */
    public void createSeries(String seriesName, User u, ArrayList<Integer> indices) {
        for (int i: indices){
                u.getEvents().get(i).setSeriesName(seriesName);
        }

    }

    /**
     * Helper method for createSeries
     *
     * @return an int corresponding to the user's selection on hourly, daily, weekly, monthly, or yearly events in their series
     */
    private int setFrequency() {
        int num;
        Scanner input = new Scanner(System.in);
        String str;
        System.out.println("Would you like this series to be:" +
                "\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly");
        do {
            System.out.println("Please choose a number between 1 and 5, inclusive.");
            while (!input.hasNextInt()) {
                str = input.next();
                System.out.println(str + " is not an option. Try again.");
            }
            num = input.nextInt();
        } while (num > 5 || num < 1);
        return num;
    }

    /**
     * Helper method for createSeries
     *
     * @return the number of events that the user wants to include in the Series
     */
    private int setNumEvents() {
        int num;
        Scanner input = new Scanner(System.in);
        String str;
        System.out.println("How many events would you like?");
        do {
            System.out.println("Please enter a minimum of 2.");
            while (!input.hasNextInt()) {
                str = input.next();
                System.out.println(str + " is not valid input. Try again.");
            }
            num = input.nextInt();
        } while (num < 2);
        return num;
    }

    /**
     * Helper method for create series
     *
     * @param dt  start time
     * @param num corresponds to user's selection of hourly, daily, weekly, monthly or yearly
     * @return a new dateTime that includes the time elapsed
     */
    private LocalDateTime elapse(LocalDateTime dt, int num) {
        switch (num) {
            case 1:
                return dt.plusHours(1);
            case 2:
                return dt.plusDays(1);
            case 3:
                return dt.plusWeeks(1);
            case 4:
                return dt.plusMonths(1);
            case 5:
                return dt.plusYears(1);
            default:
                return dt;
        }
    }

}
