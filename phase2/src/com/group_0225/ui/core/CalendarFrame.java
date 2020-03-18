package com.group_0225.ui.core;

import javax.swing.*;

public class CalendarFrame extends JFrame {

    public CalendarFrame() {
        super();

        this.add(new CalendarPanel());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(new CalendarToolBar());
    }

}
