package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.util.UIPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract calendar Controller. All other controllers extend this
 */
public abstract class CalendarController {
    protected CalendarData data;
    protected UIPresenter presenter;
    protected Map<String, Integer> Months;

    public CalendarController(CalendarData data, UIPresenter presenter) {
        this.data = data;
        this.presenter = presenter;
        this.Months = new HashMap<>();
        Months.put("Jan", 1);
        Months.put("Feb", 2);
        Months.put("Mar", 3);
        Months.put("Apr", 4);
        Months.put("May", 5);
        Months.put("Jun", 6);
        Months.put("Jul", 7);
        Months.put("Aug", 8);
        Months.put("Sep", 9);
        Months.put("Oct", 10);
        Months.put("Nov", 11);
        Months.put("Dec", 12);
    }

    /**
     * Parses an input date from panel
     * @param s
     * @return index 0: Day, index 1: Month, index 2: Year
     */
    protected List<Integer> parseDate(String s) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 11){
            result.add(Integer.parseInt(s.substring(0, 2)));
            result.add(Months.get(s.substring(3, 6)));
            result.add(Integer.parseInt(s.substring(7, 11)));
        } else {
            result.add(Integer.parseInt(s.substring(0, 1)));
            result.add(Months.get(s.substring(2, 5)));
            result.add(Integer.parseInt(s.substring(6, 10)));
        }
        return result;
    }

    /**
     * Parses an input time from panel
     * @param s
     * @return index 0: Hour, index 1: Minute, index 3: Second
     */
    protected List<Integer> parseTime(String s){
        List<Integer> result = new ArrayList<>();
        result.add(Integer.parseInt(s.substring(0, 2)));
        result.add(Integer.parseInt(s.substring(3, 5)));
        result.add(Integer.parseInt(s.substring(6, 8)));
        return result;
    }

}
