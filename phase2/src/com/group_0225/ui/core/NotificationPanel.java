package com.group_0225.ui.core;

import com.group_0225.controller.*;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel displays the information associated with a notification
 */
public class NotificationPanel extends CalendarLayoutPanel {
    /**
     * Creates a NotificationPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public NotificationPanel (ControllerContainer controllerContainer){super(controllerContainer);}
    @Override
    protected void buildPanel(List<String> inputs) {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, inputs.get(0));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        this.addLabel(c, bottomPane, 2, inputs.get(1));
        this.addLabel(c, bottomPane, 4, inputs.get(2));
        this.addLabel(c, bottomPane, 6,  inputs.get(3));
        this.addLabel(c, bottomPane, 8,  inputs.get(4));

        this.add(bottomPane, c);
    }
}
