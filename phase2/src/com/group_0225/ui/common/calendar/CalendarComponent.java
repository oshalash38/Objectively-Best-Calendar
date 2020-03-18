package com.group_0225.ui.common.calendar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoField;

public class CalendarComponent extends JPanel {

    List<JPanel> calendarComponent;

    public CalendarComponent(int month) {
        super(new GridBagLayout());

        setUpCalendar(month);
    }

    private void setUpCalendar(int month) {
        calendarComponent = new ArrayList<>();

        LocalDateTime tempLocal = LocalDateTime.of(2020, month,1,1,1);
        int weekDay = tempLocal.get(ChronoField.DAY_OF_WEEK) % 7;
        YearMonth tempYearMonth = YearMonth.of(2020, month);
        int monthLength = tempYearMonth.lengthOfMonth();

        int currDay = 1;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.1;
        c.weightx = 1;

        this.addDates();

        c.weighty = 1;

        for(int y = 0; y < 6; y++){
            if (currDay > monthLength)
                break;

            c.gridy = y + 1;

            for (int x = 0; x < 7; x ++) {
                JPanel timeComponent;

                if ((y == 0 && x < weekDay) || (currDay > monthLength)){
                    timeComponent = new JPanel();
                    timeComponent.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                } else {
                    timeComponent = new CalendarTimeComponent(currDay);
                    currDay++;
                }

                calendarComponent.add(timeComponent);
                this.add(timeComponent, c);
            }
        }
    }

    private void addDates() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.1;
        c.weightx = 1;

        String[] dayOfTheWeek = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for(String day : dayOfTheWeek) {
            JPanel dayPanel = new JPanel();
            dayPanel.add(new Label(day));

            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            this.add(dayPanel, c);
        }
    }

}
