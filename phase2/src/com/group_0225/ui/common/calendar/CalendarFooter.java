package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.core.CalendarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalendarFooter extends CalendarLayoutPanel {

    public CalendarFooter(LayoutManager2 layoutManager2, ControllerFacade controllerFacade) {
        super(layoutManager2, controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        Button nextButton = new Button("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.alterCalendarTime(1);
            }
        });

        Button prevButton = new Button("Prev");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.alterCalendarTime(-1);
            }
        });

        this.add(prevButton);
        this.add(nextButton);
    }


}
