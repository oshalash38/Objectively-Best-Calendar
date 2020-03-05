import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event in a calendar
 *
 * @author Peter Sellars
 */
public class Event implements Serializable, Comparable<Event> {
    private String eventName;
    private String seriesName = "";
    private Timing time;
    private ArrayList<Alert> alerts = new ArrayList<>();
    private ArrayList<Integer> memoIDs = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();

    /** Construct a basic event
     *
     * @param name the name of the event
     * @param t    the corresponding Timing instance
     */
    public Event(String name, Timing t) {
        this.eventName = name;
        this.time = t;
    }

    /** Construct an event as part of a series
     * @param name  the name of the event
     * @param t     the corresponding Timing instance
     * @param sname the series where this event belongs
     */
    public Event(String name, Timing t, String sname) {
        this(name, t);
        this.seriesName = sname;
    }

    /** Get the name of this event
     * @return the name of this event
     */
    public String getEventName() {
        return eventName;
    }

    /** Get this event's series name which is an empty string if it is not in a series
     * @return this event's series name
     */
    public String getSeriesName() {
        return seriesName;
    }

    /** Get the Time for this event
     * @return the Timing instance associated with this event
     */
    public Timing getTime() {
        return time;
    }

    /** Get the list of memo ids for this event
     * @return the list of memo ids associated with this event
     */
    public ArrayList<Integer> getMemoIDs() {
        return memoIDs;
    }

    /** Get the list of alerts for this event
     * @return the list of alerts/reminders associated with this event
     */
    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    /** Get the list of tags for this event
     * @return the list of tags associated with this event
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public void setTime(Timing time) {
        this.time = time;
    }

    /** Give the status of an event as an integer
     * @param dt the time being compared to this event
     * @return 0 if the event is ongoing, 1 if the event is in the future, and -1 if the event is over
     */
    public int getStatus(LocalDateTime dt) {
        return time.getStatus(dt);
    }

    /** Compare events based on their start times
     * @param e the event with which this is being compared
     * @return -1 if this event begins earlier than e, 0 if this event begins at the same time as e, 1 if the event
     * begins later than e
     */
    @Override
    public int compareTo(Event e){
        return this.time.compareStartTime(e.time);
    }

    @Override
    public String toString(){
        return "Name: " + this.eventName + "\nStart: " + this.time.getStart() + "\nEnd: " + this.time.getEnd();
    }


}

