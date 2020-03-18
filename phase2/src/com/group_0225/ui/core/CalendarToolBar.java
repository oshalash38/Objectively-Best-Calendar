package com.group_0225.ui.core;

import javax.swing.*;

public class CalendarToolBar extends JMenuBar {

    public CalendarToolBar() {
        super();

        JMenu fileMenu = new JMenu("Create");
        JMenuItem createEvent = new JMenuItem("Create Event");
        JMenuItem createAlert = new JMenuItem("Create Alert");
        JMenuItem createMemo = new JMenuItem("Create Memo");
        JMenuItem createSeries = new JMenuItem("Create Series");

        fileMenu.add(createEvent);
        fileMenu.add(createAlert);
        fileMenu.add(createMemo);
        fileMenu.add(createSeries);

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
