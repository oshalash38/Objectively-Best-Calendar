package com.group_0225.ui.common.calendar;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;

import java.awt.*;
import java.util.List;

/**
 * This class extends CalendarLayoutPanel to add functionality for the footer of the calendar
 */
public class CalendarFooter extends CalendarLayoutPanel {
    /**
     * Creates a new CalendarFooter
     * @param layoutManager2 a LayoutManager2
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarFooter(LayoutManager2 layoutManager2, ControllerContainer controllerContainer) {
        super(layoutManager2, controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        Button nextButton = new Button("Next");
        nextButton.addActionListener(e -> gridController.alterMonth(1));

        Button prevButton = new Button("Prev");
        prevButton.addActionListener(e -> gridController.alterMonth(-1));

        this.add(prevButton);
        this.add(nextButton);
    }


}
