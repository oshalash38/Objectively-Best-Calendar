package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventListPanel extends CalendarLayoutPanel {

    public EventListPanel(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

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

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);
        this.add(panel, c);
    }


}
