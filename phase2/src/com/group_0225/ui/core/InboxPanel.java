package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InboxPanel extends CalendarLayoutPanel {
    public InboxPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        MessagingController messagingController = controllerContainer.getMessagingController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("Inbox"));
        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(c, bottomPane, 1, viewModel.get("Requests"));


        for (int i = 0; i < inputs.size(); i++){
            Button button = addButton(c, bottomPane, i + 2, inputs.get(0));
        }

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
