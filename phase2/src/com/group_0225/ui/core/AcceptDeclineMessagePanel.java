package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * This class displays a dialog that allows the user to view an invitation for an event, and accept/decline the invitation
 */
public class AcceptDeclineMessagePanel extends CalendarLayoutPanel {
    /**
     * Creates an AcceptDeclineMessagePanel
     * @param controllerContainer a ControllerContainer instance
     */
    public AcceptDeclineMessagePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    /**
     * Builds this CalendarLayoutPanel
     * @param inputs the inputs to display
     */
    @Override
    protected void buildPanel(List<String> inputs) {
        MessagingController messagingController = controllerContainer.getMessagingController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        //title
        buildTitle(constraints,viewModel.get("Inbox"));

        //display message
        JTextArea message = addTextArea(inputs.get(0),bottomPane,2,constraints);
        message.setEditable(false);

        //display event
        addLabel(constraints,bottomPane,3,viewModel.get("Event information: "));
        JTextArea event = addTextArea(inputs.get(1),bottomPane,4,constraints);
        event.setEditable(false);

        //optional reply
        addLabel(constraints,bottomPane,5, viewModel.get("Optional reply: "));
        JTextArea reply = addTextArea("",bottomPane,6,constraints);
        reply.setEditable(true);

        //accept/decline
        Button accept = addButton(constraints,bottomPane,7, viewModel.get("Accept invitation: "));
        accept.addActionListener(e -> messagingController.acceptMessage(Arrays.asList(inputs.get(0),reply.getText()),
                controllerContainer.getCalendarGridController()));
        Button decline = addButton(constraints,bottomPane,8,viewModel.get("Decline invitation: "));
        decline.addActionListener(e -> {
            //pass the message and the reply
            messagingController.declineMessage(Arrays.asList(inputs.get(0),reply.getText()));
        });
        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
