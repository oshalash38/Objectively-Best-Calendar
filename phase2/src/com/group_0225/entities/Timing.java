package com.group_0225.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Here is the main class we will use to keep track of anything that has to do with time
 *
 * @author Daniel Shoichet
 *
 */
public class Timing implements Serializable{

    private LocalDateTime start;

    private LocalDateTime end = null;

    /**
     * Creates a timing with a start time and an end time of null
     */
    public Timing(){
        this.start= LocalDateTime.now();
    }

    /**
     * Creates a Timing with the given start and end of null
     * @param start start time
     */
    public Timing(LocalDateTime start){
        this.start = start;
    }

    /**
     * Creates a Timing with start and end
     * @param start the start of this timing
     * @param end the end of this timing
     */
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
        if(end != null) {
            if (start.compareTo(dt) <= 0 && end.compareTo(dt) >= 0) {
                return 0;
            } else if (end.compareTo(dt) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        else{
            return start.compareTo(dt);
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

    public boolean intersect(Timing o) {

        LocalDateTime thisStart = this.start;
        LocalDateTime thisEnd = this.end == null ? this.start : this.end;

        LocalDateTime otherStart = o.start;
        LocalDateTime otherEnd = o.end == null ? o.start : o.end;

        return (thisStart.compareTo(otherEnd) <= 0) && (thisEnd.compareTo(otherStart) >= 0);
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
        if (end == null) {
            return new Timing(this.start.plus(freq));
        }
        else{
            if (freq.toDays() == 30){
                return new Timing(this.start.plusMonths(1), this.end.plusMonths(1));
            }
            return new Timing(this.start.plus(freq), this.end.plus(freq));
        }
    }

    /**
     * a setter for end
     * @param end the new end
     */
    public void setEnd(LocalDateTime end){
        this.end = end;
    }
    /**
     *
     * @return a string with the start date and time.
     */
    public String toString(){
        return start.getDayOfMonth() + " " + start.getMonth() + " " + start.getYear() + ", at: " + start.getHour() + ":" + start.getMinute();
    }
    /**
     *
     * @return a list with all the start and end time values
     */
    public List<String> getInfo(){
        List<String> info = new ArrayList<>();

        info.add(start.getDayOfMonth() + "");
        info.add(start.getMonthValue() + "");
        info.add(start.getYear() + "");
        info.add(start.getHour() + "");
        info.add(start.getMinute() + "");

        if(end != null) {
            info.add(end.getDayOfMonth() + "");
            info.add(end.getMonthValue() + "");
            info.add(end.getYear() + "");
            info.add(end.getHour() + "");
            info.add(end.getMinute() + "");
        }

        return info;
    }

    /**
     * A setter for start
     * @param start the new start
     */
    public void setStart(LocalDateTime start){
        this.start = start;
    }

}
