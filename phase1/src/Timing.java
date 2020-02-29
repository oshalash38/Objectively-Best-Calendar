import java.time.*;
/**
 * Here is the main class we will use to keep track of anything that has to do with time
 *
 * @author Daniel Shoichet
 *
 */
public class Timing {

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


}
