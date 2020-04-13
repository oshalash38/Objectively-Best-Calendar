package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SendMessagePanel extends CalendarLayoutPanel {

    public SendMessagePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }


    @Override
    protected void buildPanel(List<String> inputs) {

        MessagingController messagingController = controllerContainer.getMessagingController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("SendMessage"));

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField username = addTextField(c, bottomPane, 1, viewModel.get("EnterUsername"), "");
        addLabel(c, bottomPane, 2, viewModel.get("EnterEvent"));
        int numOfEvents = Integer.parseInt(inputs.get(0));
        JComboBox<String> choice = addDropDown(inputs.subList(1, numOfEvents + 1), bottomPane, 3, c);
        addLabel(c, bottomPane, 4, viewModel.get("EnterMessage"));
        JTextArea message = addTextArea(c, bottomPane, 5);
        Button send = addButton(c, bottomPane, 6, viewModel.get("Send"));


        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> input = new ArrayList<>();
                input.add(username.getText());
                input.add(inputs.get(choice.getSelectedIndex() + numOfEvents + 1));
                input.add(message.getText());
                messagingController.sendMessage(input);
            }
        });


        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
