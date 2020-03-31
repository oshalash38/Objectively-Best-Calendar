package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarToolBar extends JMenuBar {

    ControllerFacade controllerFacade;

    public CalendarToolBar(ControllerFacade controllerFacade) {
        super();
        this.controllerFacade = controllerFacade;

        JMenu fileMenu = new JMenu("Create");
        JMenuItem createEvent = new JMenuItem("Create Event");
        JMenuItem createAlert = new JMenuItem("Create Alert");
        JMenuItem createMemo = new JMenuItem("Create Memo");
        JMenuItem createSeries = new JMenuItem("Create Series");

        fileMenu.add(createEvent);
        fileMenu.add(createAlert);
        fileMenu.add(createMemo);
        fileMenu.add(createSeries);

        createEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.createEvent();
            }
        });

        this.add(fileMenu);

        JMenu view = new JMenu("View");
        JMenuItem weeks = new JMenuItem("Set To Weeks");
        JMenuItem months = new JMenuItem("Set To Months");

        view.add(weeks);
        view.add(months);

        this.add(view);

        JMenu testing = new JMenu("Testing");
        JMenuItem speedUp = new JMenuItem("Speed Up Time");
        JMenuItem resumeNormalTime = new JMenuItem("Resume Normal Time");

        testing.add(speedUp);
        testing.add(resumeNormalTime);

        this.add(testing);
    }
}
