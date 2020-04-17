package com.group_0225.manager;

import com.group_0225.entities.*;

import java.time.Duration;
import java.time.LocalDateTime;
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
     * create a series from scratch
     * @param calendarData a CalendarData instance, passed to the EventManager
     * @param seriesName the name of the series
     * @param fSelection the frequency of the events
     * @param startDate the start date in Day-Month-Year form
     * @param month the month of the startDate as an integer
     * @param startTime the startTime in HH:MM:SS
     * @param durationDays the number of days that each event lasts
     * @param durationHMS the duration in HH:MM:SS form
     * @param numEvents the number of events in the series
     * @param em an EventManager instance
     */
    public void createSeries(CalendarData calendarData, String seriesName, String fSelection, String startDate, Integer month, String startTime, String durationDays, String durationHMS,String numEvents, EventManager em) {
        ArrayList<Event> lst = new ArrayList<>();

        //initialize frequency
        Duration frequency = parseFrequency(fSelection);
        //initialize startDate
        LocalDateTime startLDT = parseStartLDT(startDate, month, startTime);
        //initialize duration
        Duration elapsed = parseDuration(durationDays,durationHMS);
        Timing first = tf.createTiming(startLDT,elapsed);
        em.createEvent(calendarData,seriesName + "#" + 1,first,seriesName);

        int ne = Integer.parseInt(numEvents);
        for (int i = 0; i< ne - 1; i++){
            first = first.addToThis(frequency);
            em.createEvent(calendarData,seriesName + "#" + (i + 2),first,seriesName); //TODO: Do something about duplicate parameter
        }

    }
    private Duration parseFrequency(String fSelection){Duration length;
        switch (Integer.parseInt(fSelection)) {
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
        return length;
    }
    private LocalDateTime parseStartLDT(String startDate, Integer month, String startTime){
        String[] hms = startDate.split("-");
        int day = Integer.parseInt(hms[0]);
        int year = Integer.parseInt(hms[2]);
        int hour = Integer.parseInt(startTime.substring(0,2));
        int minute = Integer.parseInt(startTime.substring(3,5));
        int second = Integer.parseInt(startTime.substring(6,8));
        return LocalDateTime.of(year,month,day,hour,minute,second);
    }
    private Duration parseDuration(String durationDays, String durationHMS){
        int days = Integer.parseInt(durationDays);
        int hours = Integer.parseInt(durationHMS.substring(0,2));
        int minutes = Integer.parseInt(durationHMS.substring(3,5));
        int seconds = Integer.parseInt(durationHMS.substring(6,8));
        Duration ret = Duration.ZERO;
        ret = ret.plusDays(days);
        ret = ret.plusHours(hours);
        ret = ret.plusMinutes(minutes);
        ret = ret.plusSeconds(seconds);
        return ret;
    }

    /**
     * Create a series from a list of events
     * @param data a CalendarData instance
     * @param seriesName the name of the new series
     * @param inputs the list of ids that the user has selected to form a new series
     */
    public void createSeries(CalendarData data, String seriesName, List<String> inputs) {
        List<Integer> lst = toIntegers(inputs);
        for (Integer i: lst){
            data.getEvents().get(i).setSeriesName(seriesName);
        }

    }

    /**
     * Getter for all series names that the current user has built
     * @param data a CalendarData instance
     * @param eventManager an EventManager instance
     * @return the series names
     */
    public List<String> getAllSeriesNames(CalendarData data, EventManager eventManager){
        List<String> uniqueNames = new ArrayList<>();
        List<Event> events = eventManager.getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar());
        for (Event e: events){
            if (!uniqueNames.contains(e.getSeriesName())){
                uniqueNames.add(e.getSeriesName());
            }
        }
        return uniqueNames;
    }
    private List<Integer> toIntegers(List<String> args){
        List<Integer> output = new ArrayList<>();
        for (String s: args){
            output.add(Integer.parseInt(s));
        }return output;
    }




}
