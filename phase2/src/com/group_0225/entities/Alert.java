package com.group_0225.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * An abstract class that sets parameters required by different types of alerts.
 *
 * @author Daniel Shoichet
 *
 */
public abstract class Alert implements Serializable, Comparable<Alert>{
    protected String message;


    /**
     * Construct a basic alert with a time and a message
     * @param message The message to be displayed when the alert is pushed
     */
    public Alert(String message){
        this.message = message;
    }

    /**
     * Construct a basic alert with a time
     *
     */
    public Alert(){
        this.message = null;
    }

    /**
     * Returns the next time this alert will be pushed
     *
     * @return the next time this alert will be pushed
     */
    public abstract Timing getNextTime();


    /** Give the status of an alert as an integer
     * @param dt the time being compared to this event
     * @return 0 if the event is ongoing, 1 if the event is in the future, and -1 if the event is over
     */
    public abstract int getStatus(LocalDateTime dt);


    /**
     * Returns the string that should be pushed to the user.
     *
     *
     * @return The message associated with this Alert
     */
    public String pushReminder(){
        if (message != null){
            return message;
        }
        return "no message";
    }

    /**
     *
     * @param message the new message for this alert.
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     *
     * @return the type of this alert
     */
    public abstract String getType();

    /**
     *
     * @return a list of parameters that this alert stores
     */
    public abstract List<String> getParameters();

    /**
     *
     * @param t the new time of this reminder
     */
    public abstract void setTime(Timing t);


}
