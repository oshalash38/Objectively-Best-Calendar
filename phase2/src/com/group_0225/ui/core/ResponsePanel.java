package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel allows the user to view a response to their invitation
 */
public class ResponsePanel extends CalendarLayoutPanel {
    /**
     * Creates a ResponsePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public ResponsePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        MessagingController messagingController = controllerContainer.getMessagingController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        JPanel bottomPane = new JPanel(new GridBagLayout());


        addLabel(c, bottomPane, 4, viewModel.get("From") + inputs.get(0));
        addLabel(c, bottomPane, 6, viewModel.get("Response"));
        JTextArea response = addTextArea(inputs.get(1), bottomPane, 8, c);
        response.setEditable(false);

        c.gridy = 1;
        this.add(bottomPane, c);

    }
}
