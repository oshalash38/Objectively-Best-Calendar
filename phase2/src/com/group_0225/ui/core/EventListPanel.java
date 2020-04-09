package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventListPanel extends CalendarLayoutPanel {

    public EventListPanel(ControllerContainer controllerContainer) {
        super(new GridBagLayout(), controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;

        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.darkGray);

        for (int i = 0; i < inputs.size(); i++) {
            c.gridy = i;
            JButton eventPreview = new JButton(inputs.get(i));
            parent.add(eventPreview, c);
        }

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);
        this.add(panel, c);
    }
}
