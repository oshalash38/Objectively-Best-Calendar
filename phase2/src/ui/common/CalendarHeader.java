package ui.common;

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
        c.anchor = GridBagConstraints.CENTER;

        this.add(new Label(MONTHS[month - 1]), c);
    }
}
