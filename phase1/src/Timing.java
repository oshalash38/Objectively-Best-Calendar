
import java.io.Serializable;
import java.time.*;
/**
 * Here is the main class we will use to keep track of anything that has to do with time
 *
 * @author Daniel Shoichet
 *
 */
public class Timing implements Serializable{

    private LocalDateTime start;

    private LocalDateTime end;

    public Timing(LocalDateTime start){
        this.start = start;
        this.end = null;
    }

    public Timing(LocalDateTime start, LocalDateTime end){
        this.start = start;
        this.end = end;
    }

    /**
     * This returns a LocalDateTime that represent the start of an event.
     *
     * @return the start time of this event.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * This returns a LocalDateTime that represent the end of an event.
     *
     * @return the end time of this event. If there is no endTime, this will return null.
     */
    public LocalDateTime getEnd() {
        return end;
    }


    /** Give the status of an event as an integer
     * @param dt the time being compared to this event
     * @return 0 if the event is ongoing, 1 if the event is in the future, and -1 if the event is over
     */
    public int getStatus(LocalDateTime dt) {
        if (start.compareTo(dt) <= 0 && end.compareTo(dt) >= 0) {
            return 0;
        } else if (end.compareTo(dt) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /** Compare events based on their start times
     * @param o the event with which this is being compared
     * @return -1 if this event begins earlier than o, 0 if this event begins at the same time as o, 1 if the event
     * begins later than o
     */
    public int compareStartTime(Timing o){
        return this.start.compareTo(o.start);
    }

    /** Compare events based on their end times
     * @param o the event with which this is being compared
     * @return -1 if this event begins earlier than o, 0 if this event begins at the same time as o, 1 if the event
     * begins later than o
     */
    public int compareEndTime(Timing o){
        return this.end.compareTo(o.end);
    }

    /**
     *
     * @param freq the amount of time to be added to the old Timing
     * @return a new Timing instance with freq added to the start time
     */
    public Timing addToThis(Duration freq){
        return new Timing(this.start.plus(freq));
    }

    /**
     *
     * @return a string with the start date and time.
     */
    public String toString(){
        return new String(start.getDayOfMonth()+" " + start.getMonth() + start.getYear() + ", at: " + start.getHour() + ":"+ start.getMinute());
    }


}
