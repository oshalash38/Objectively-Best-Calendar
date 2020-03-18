package com.group_0225.ui.core;

import javax.swing.*;
import java.awt.*;

public class EventListDialog extends JDialog {

    public EventListDialog() {
        super();
        this.setSize(400, 400);
        this.setLocation(300, 200);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;

        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.YELLOW);

        for (int i = 0; i < 30; i++) {
            c.gridy = i;
            JButton eventPreview = new JButton("Event " + i);
            parent.add(eventPreview, c);
        }

        JScrollPane panel = new JScrollPane(parent);
        this.add(panel);
    }

}
