package ui.core;

import ui.common.CalendarComponent;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {

    public CalendarPanel() {
        super(new GridBagLayout());
        this.setBackground(Color.YELLOW);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.weightx = 1;

        JPanel testOptions = new JPanel();
        testOptions.setBackground(Color.RED);

        this.add(new Label("Calendar"), c);
        c.gridy = 1;
        c.weighty = 5;
        this.add(new CalendarComponent(4), c);
        c.gridy = 2;
        c.weighty = 1;
        this.add(testOptions, c);
    }

}
