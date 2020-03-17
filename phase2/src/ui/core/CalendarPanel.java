package ui.core;

import ui.common.CalendarComponent;
import ui.common.CalendarFooter;
import ui.common.CalendarHeader;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {

    int currTime = 3;

    CalendarHeader header;
    CalendarComponent component;
    CalendarFooter footer;

    public CalendarPanel() {
        super(new GridBagLayout());

        refreshCalendar();
    }

    public void refreshCalendar() {

        if (header != null)
            this.remove(header);
        header = new CalendarHeader(currTime);

        if (component != null)
            this.remove(component);
        component = new CalendarComponent(currTime);

        if (footer != null)
            this.remove(footer);
        footer = new CalendarFooter(this);

        displayCalendar();
    }

    private void displayCalendar() {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.weightx = 1;

        this.add(header, c);
        header.setVisible(true);
        c.gridy = 1;
        c.weighty = 5;
        this.add(component, c);
        c.gridy = 2;
        c.weighty = 1;
        this.add(footer, c);

        this.revalidate();
    }

    public int getCurrentTime() { return currTime; }
    public int getCurrentTimeMax() { return 12; }
    public void setCurrentTime(int currTime) { this.currTime = currTime; }
}
