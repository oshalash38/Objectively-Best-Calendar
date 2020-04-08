package com.group_0225.ui.common.calendar;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalendarFooter extends CalendarLayoutPanel {

    public CalendarFooter(LayoutManager2 layoutManager2, ControllerContainer controllerContainer) {
        super(layoutManager2, controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        Button nextButton = new Button("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridController.alterMonth(1);
            }
        });

        Button prevButton = new Button("Prev");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridController.alterMonth(-1);
            }
        });

        this.add(prevButton);
        this.add(nextButton);
    }


}
