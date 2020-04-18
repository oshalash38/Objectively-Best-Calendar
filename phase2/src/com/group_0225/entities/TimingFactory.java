package com.group_0225.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
/**
 * This class creates instances of things related to Timing with given string input
 *
 * @author Daniel Shoichet
 *
 */
public class TimingFactory {

    public void TimingFactory(){}

    /**
     * returns an instance of Timing with a start time but no end time
     *
     *
     * @param year year of this Timing
     * @param month month of this Timing
     * @param dayOfMonth dayOfMonth of this Timing
     * @param hour the hour for this Timing
     * @param minute the minute for this Timing
     * @return a new Timing object as specified
     */
    public Timing createTiming(int year, int month, int dayOfMonth, int hour, int minute){
        YearMonth ym = YearMonth.of(year,month);
        if (dayOfMonth > ym.lengthOfMonth()){
            dayOfMonth = ym.lengthOfMonth();
        }
        return new Timing(LocalDateTime.of(year, Month.of(month), dayOfMonth, hour, minute));
    }

    /**
     * Creates a new Timing
     * @param time the time
     * @return the Timing
     */
    public Timing createTiming(LocalDateTime time){
        return new Timing(time);
    }

    /**
     * Creates a new Timing with a defined start and end from a LocalDateTime and Duration instance
     * @param start LocalDateTime instance
     * @param elapse how long the event lasts
     * @return
     */
    public Timing createTiming(LocalDateTime start, Duration elapse){
        LocalDateTime end = start.plus(elapse);
        return new Timing(start, end);
    }
    /**
     * returns an instance of Timing with a start time and an end time. The first set of parameters is for start time
     * and the second set of parameters is for end time.
     *
     * @param year1 the year of the start of this timing
     * @param month1 the month of the start of this timing
     * @param dayOfMonth1 the dayOfMonth of the start of this timing
     * @param hour1 the hour of the start of this timing
     * @param minute1 the minute of the start of this timing
     * @param year2 the year of the end of this timing
     * @param month2 the month of the end of this timing
     * @param dayOfMonth2 the dayOfMonth of the end of this timing
     * @param hour2 the hour of the end of this timing
     * @param minute2 the minute of the end of this timing
     * @return
     */
    public Timing createTiming(int year1, int month1, int dayOfMonth1, int hour1, int minute1,
                               int year2, int month2, int dayOfMonth2, int hour2, int minute2){
        YearMonth monthOfYear1 = YearMonth.of(year1, month1);
        YearMonth monthOfYear2 = YearMonth.of(year2, month2);

        if(dayOfMonth1 > monthOfYear1.lengthOfMonth())
            dayOfMonth1 = monthOfYear1.lengthOfMonth();
        if(dayOfMonth2 > monthOfYear2.lengthOfMonth())
            dayOfMonth2 = monthOfYear2.lengthOfMonth();

        LocalDateTime start = LocalDateTime.of(year1, Month.of(month1), dayOfMonth1, hour1, minute1);
        LocalDateTime end = LocalDateTime.of(year2, Month.of(month2), dayOfMonth2, hour2, minute2);

        if (start.isAfter(end)){
            return null;
        } else {
            return new Timing(start, end);
        }
    }
    
}
