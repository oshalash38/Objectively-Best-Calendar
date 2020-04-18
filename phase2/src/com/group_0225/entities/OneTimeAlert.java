package com.group_0225.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An alert that only happens once on a given date and time.
 *
 * @author Daniel Shoichet
 */
public class OneTimeAlert extends Alert {

    private Timing time;

    /**
     * Construct a basic OneTimeAlert with a time and a message
     * @param time The time of when this alert should take place
     * @param message The message to be displayed when the alert is pushed
     */
    public OneTimeAlert(Timing time, String message){
        super(message);
        this.time = time;
    }

    /**
     * Construct a OneTimeAlert alert with a time
     * @param time The time of when this alert should take place
     */
    public OneTimeAlert(Timing time){
        super();
        this.time = time;
    }

    /** Compare Alerts based on their start times
     * @param o the event with which this is being compared
     * @return -1 if this event begins earlier than o, 0 if this event begins at the same time as o, 1 if the event
     * begins later than e
     */
    @Override
    public int compareTo(Alert o){
        return time.compareStartTime(o.getNextTime());
    }

    /**
     * Returns the next time this alert will be pushed
     *
     * @return the next time this alert will be pushed
     */
    @Override
    public Timing getNextTime(){
        return time;
    }


    /** Give the status of an event as an integer
     * @param dt the time being compared to this event
     * @return 0 if the event is ongoing, 1 if the event is in the future, and -1 if the event is over
     */
    @Override
    public int getStatus(LocalDateTime dt) {
        return time.getStatus(dt);
    }

    /**
     * Return the type description of this alert
     * @return a String describing this alert as a one-time occurrence
     */
    @Override
    public String getType() {
        return "One time alert";
    }

    /**
     * Return the parameters of this alert
     * @return the time and message of the alert as a list of strings
     */
    @Override
    public List<String> getParameters() {
        List<String> output = new ArrayList<>();
        output.add("time");
        output.add("message");
        return output;
    }

    /**
     * @param t the new time of this reminder
     */
    @Override
    public void setTime(Timing t) {
        this.time = t;
    }
}
