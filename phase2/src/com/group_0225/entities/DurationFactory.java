package com.group_0225.entities;

import java.time.Duration;

public class DurationFactory {
    public Duration createDuration(int day, int hour, int minute){
        Duration d = Duration.ZERO;
        d = d.plusDays(day);
        d = d.plusHours(hour);
        d = d.plusMinutes(minute);
        return d;
    }
}
