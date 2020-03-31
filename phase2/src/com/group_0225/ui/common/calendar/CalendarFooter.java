package com.group_0225.ui.common.calendar;

import com.group_0225.ui.core.CalendarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarFooter extends JPanel {

    private CalendarPanel calendar;

    public CalendarFooter(CalendarPanel calendar) {
        super();

        this.calendar = calendar;

        Button nextButton = new Button("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextTime();
                calendar.refreshCalendar();
            }
        });

        Button prevButton = new Button("Prev");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevTime();
                calendar.refreshCalendar();
            }
        });

        this.add(prevButton);
        this.add(nextButton);
    }

    private void nextTime() {
        int next = (calendar.getCurrentTime() % calendar.getCurrentTimeMax()) + 1;

        calendar.setCurrentTime(next);
    }

    private void prevTime() {
        int next = calendar.getCurrentTime() - 1;

        if(next <= 0)
            next = calendar.getCurrentTimeMax();

        calendar.setCurrentTime(next);
    }
}
