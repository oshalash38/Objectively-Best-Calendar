package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

/**
 * This panel allows the user to view any replies or received invitations.
 */
public class InboxPanel extends CalendarLayoutPanel {
    /**
     * Creates a new InboxPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
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

        addLabel(c, bottomPane, 0, viewModel.get("Requests"));
        int requestUsers = inputs.indexOf("RequestUsers");
        int requestMessages = inputs.indexOf("RequestMessages");
        int responseUsers = inputs.indexOf("ResponseUsers");
        int responseMessages = inputs.indexOf("ResponseMessages");
        int responsePreviousMessages = inputs.indexOf("ResponseExMessages");
        Map<Button, String> requestButtons = new HashMap<>();

        int i = requestUsers + 1;
        int j = requestMessages + 1;
        for (; i < requestMessages; i++){
            Button button = addButton(c, bottomPane, i + 2, inputs.get(i));
            requestButtons.put(button, inputs.get(j));
            j++;
        }

        for (Map.Entry<Button, String> entry : requestButtons.entrySet()){
            entry.getKey().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messagingController.pushAcceptDeclinePanel(Collections.singletonList(entry.getValue()));
                }
            });
        }


        addLabel(c, bottomPane, i+ 2, viewModel.get("Responses"));
        Map<Button, String> responseButtons = new HashMap<>();
        int k = responseUsers + 1;
        int l = responseMessages + 1;

        for (; k < responseMessages; k++){
            System.out.println(inputs.get(l));
            if (inputs.get(l) != null){
                Button button = addButton(c, bottomPane, k + 2, inputs.get(k));
                responseButtons.put(button, inputs.get(l));
            }
            l++;
        }

        for (Map.Entry<Button, String> entry : responseButtons.entrySet()){
            entry.getKey().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> input = new ArrayList<>();
                    input.add(entry.getKey().getLabel());
                    input.add(entry.getValue());
                    messagingController.pushResponsePanel(input);
                }
            });
        }

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
