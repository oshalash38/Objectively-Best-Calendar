package ui.common.calendar;

import javax.swing.*;
import java.awt.*;

public class CalendarTimeComponent extends JPanel {

    public CalendarTimeComponent(int day) {
        super(new GridBagLayout());

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.PAGE_START;

        Label dayNumber = new Label(day + "");
        this.add(dayNumber, c);
    }

}
