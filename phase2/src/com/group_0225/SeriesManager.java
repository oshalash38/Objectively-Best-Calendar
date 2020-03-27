package com.group_0225;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods for creating a series
 * @author Peter Sellars
 */
public class SeriesManager {

    private TimingFactory tf = new TimingFactory();
    private DurationFactory df = new DurationFactory();

    /**
     * Creates a list of new Events that form a series
     *
     * @param seriesName the name of the new series
     * @param dur        the duration of each event in the series
     * @param start      the beginning of the series
     * @return the list of events forming this series
     */
    public void createSeries(User u, String seriesName, List<Integer> dur, List<Integer> start, int fSelection, int neSelection, EventManager eventManager) {
        ArrayList<Event> lst = new ArrayList<>();
        Timing temp = tf.createTiming(start.get(0), start.get(1), start.get(2), start.get(3), start.get(4),
                start.get(0), start.get(1), dur.get(0)+start.get(2), dur.get(1)+start.get(3), dur.get(2)+start.get(4));
        eventManager.createEvent(u, seriesName, temp, seriesName);
        Duration length;
        switch(fSelection){
            case 1:
                length = Duration.ofHours(1);
                break;
            case 2:
                length = Duration.ofDays(1);
                break;
            case 3:
                length = Duration.ofDays(7);
                break;
            case 4:
                length = Duration.ofDays(30);
                break;
            default:
                length = Duration.ofDays(365);
                break;
        }
        for (int i = 0; i< neSelection; i++){
            temp = temp.addToThis(length);
            eventManager.createEvent(u,seriesName+ i, temp, seriesName);
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

    /**
     * Removes the given seriesName from its corresponding events
     * @param seriesName the name of the series to be disbanded
     * @param u the user
     */
    public void deleteSeriesAffiliation(String seriesName, User u){
        List<Event> lst = u.getEvents();
        for (Event e: lst){
            if (e.getSeriesName().equals(seriesName)){
                e.setSeriesName("");
            }
        }
    }

    /**
     * Removes all the events with the given seriesName
     * @param seriesName the name of the series to be completely deleted
     * @param u the user
     * @param em an EventManager instance to delete each event
     */
    public void deleteSeries(String seriesName, User u, EventManager em){
        for (Event e: u.getEvents()){
            if (e.getSeriesName().equals(seriesName)){
                em.deleteEvent(e,u,this);
            }
        }
    }
    //To be used in changeFrequency, changeDuration
    private List<Event> collectSeriesFromUser(String seriesName, User u){
        ArrayList<Event> lst = new ArrayList<>();
        for (Event e: u.getEvents()){
            if (e.getSeriesName().equals(seriesName)){
                lst.add(e);
            }
        }
        return lst;
    }

    /**
     * If there is one event in a series, then remove that affiliation
     * @param seriesName the series in question
     * @param u the user
     */
    public void checkAlone(String seriesName, User u){
        int count = 0;
        for (Event e: u.getEvents()){
            if (e.getSeriesName().equals(seriesName)){
                count ++;
            }
        }
        if (count == 1){
            deleteSeriesAffiliation(seriesName, u);
        }
    }
}
