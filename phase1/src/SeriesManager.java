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
    public void createSeries(String seriesName, User u, List<Integer> indices) {
        for (int i: indices){
                u.getEvents().get(i).setSeriesName(seriesName);
        }

    }



}
