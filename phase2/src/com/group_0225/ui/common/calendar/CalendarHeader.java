package com.group_0225.ui.common.calendar;

import javax.swing.*;
import java.awt.*;

public class CalendarHeader extends JPanel {

    private static final String[] MONTHS = new String[] {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};

    public CalendarHeader(int month) {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        Label timeLabel = new Label(MONTHS[month - 1]);
        timeLabel.setAlignment(Label.CENTER);

        this.add(timeLabel, c);
    }
}
