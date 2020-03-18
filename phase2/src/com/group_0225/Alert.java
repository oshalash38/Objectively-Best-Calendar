package com.group_0225;

import java.io.Serializable;
import java.time.LocalDateTime;

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
}
