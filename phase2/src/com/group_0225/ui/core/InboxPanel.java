package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
        Map<Button,String> buttons = new HashMap<>();

        for (int i = 0; i < inputs.size()/2; i++){
            buttons.put(addButton(c, bottomPane, i + 2, inputs.get(i)),inputs.get(inputs.size()/2+i));
        }
        for (Button b: buttons.keySet()){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> message = Arrays.asList(buttons.get(b));
                    messagingController.pushAcceptDeclinePanel(message);
                }
            });
        }

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
