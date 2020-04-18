package com.group_0225.entities;

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
    private List<String> tags = new ArrayList<>();
    private Status status = Status.UPCOMING;
    private Integer ID;

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

    /**
     * Getter for id of this event
     * @return id of this event
     */
    public Integer getID(){return this.ID;}

    /**
     * Setter for the id of this event
     * @param id the new id of this event
     */
    public void setID(Integer id){this.ID = id;}

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
    public List<String> getTags() {
        return tags;
    }

    /**
     * A setter method for eventName
     * @param eventName the new eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * A setter method for seriesName
     * @param seriesName the new seriesName
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
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

    public int compareTo(Timing timing){
        if (this.time.compareStartTime(timing) > 0 || this.time.compareEndTime(timing) < 0){
            return 1;
        }
        else {
            return 0;
        }
    }


    /**
     * Checks the time of the event relative to the current time and updates the status accordingly.
     */
    public void updateStatus(CalendarData calendarData){
        int status = time.getStatus(calendarData.getLocalTime().getStart());
        if (status == 0){
                this.status = Status.CURRENT;}
        else if ( status == 1){
                this.status = Status.UPCOMING;}
        else{
                this.status = Status.PAST;}
        }


    /**
     * Returns this event's status
     * @return This event's status
     */
    public Status getStatus(){return status;}

    /**
     * A getter method for startTime
     * @return startTime of this event
     */
    public String getStartTimeString(){
        return this.time.getStart().toString().substring(11, 16);
    }

    /**
     * A getter method for endTime
     * @return endTime of this event
     */
    public String getEndTimeString(){
        return this.time.getEnd().toString().substring(11, 16);
    }

    /**
     * A getter method for startDate
     * @return startDate of this event
     */
    public String getStartDateString(){
        String year = this.time.getStart().toString().substring(0, 4);
        String month = this.time.getStart().toString().substring(5, 7);
        String day = this.time.getStart().toString().substring(8, 10);
        return day+"-"+month+"-"+year;
    }

    /**
     * A getter method for endDate
     * @return endDate of this event
     */
    public String getEndDateString(){
        String year = this.time.getEnd().toString().substring(0, 4);
        String month = this.time.getEnd().toString().substring(5, 7);
        String day = this.time.getEnd().toString().substring(8, 10);
        return day+"-"+month+"-"+year;
    }
    public void addTags(List<String> input){
        this.tags.addAll(input);
    }
    public void setTags(List<String> input){
        this.tags = input;
    }

    /**
     * Adds a memo to the list of memos for this event
     * @param memoID the ID of the memo
     */
    public void addMemoID(int memoID) {
        this.memoIDs.add(memoID);
    }

    public void removeMemoID(Integer key) {
        this.memoIDs.remove(key);
    }
}

