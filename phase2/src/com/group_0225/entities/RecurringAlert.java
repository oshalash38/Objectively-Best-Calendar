package com.group_0225.entities;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * An alert that happens every specified amount of time until the event associated with this Alert stops being checked
 *
 * @author Daniel Shoichet
 */
public class RecurringAlert extends Alert {
    private Timing nextTime;
    private Duration freq;

    public RecurringAlert(Timing time, String message, Duration freq){
        super(message);
        nextTime = time;
        this.freq = freq;
    }

    public RecurringAlert(Timing time, Duration freq){
        super();
        nextTime = time;
        this.freq = freq;
    }

    /**
     * Compare Alerts based on their start times
     *
     * @param o the event with which this is being compared
     * @return -1 if this event begins earlier than o, 0 if this event begins at the same time as o, 1 if the event
     * begins later than e
     */
    @Override
    public int compareTo(Alert o) {
        return nextTime.compareStartTime(o.getNextTime());
    }

    /**
     * Returns the next time this alert will be pushed
     *
     * @return the next time this alert will be pushed
     */
    @Override
    public Timing getNextTime() {
        return nextTime;
    }

    /** Give the status of an event as an integer
     *
     * @param dt the time being compared to this event
     * @return 0 if the event is ongoing, 1 if the event is in the future, and -1 if the event is over
     */
    @Override
    public int getStatus(LocalDateTime dt) {
        if (nextTime.getStatus(dt) == -1){
            nextTime = nextTime.addToThis(freq);
            return -1;
        }
        else if(nextTime.getStatus(dt) == 0){
            nextTime = nextTime.addToThis(freq);
            return 0;
        }
        else{
            return 1;
        }
    }
}