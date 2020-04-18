package com.group_0225.entities;

import java.time.Duration;

/**
 * This class uses the Factory design pattern to construct a Duration object from ints.
 */
public class DurationFactory {
    /**
     * Returns a Duration object representing the date info given by parameters
     * @param day the selected day
     * @param hour the selected hour
     * @param minute the selected minute
     * @return the Duration object representing the date info given
     */
    public Duration createDuration(int day, int hour, int minute){
        Duration d = Duration.ZERO;
        d = d.plusDays(day);
        d = d.plusHours(hour);
        d = d.plusMinutes(minute);
        return d;
    }
}
