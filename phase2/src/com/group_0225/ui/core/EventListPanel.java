package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class EventListPanel extends CalendarLayoutPanel {

    public EventListPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController eventController = controllerContainer.getEventsController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;


        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.darkGray);

        if (inputs.size() == 0){
            JLabel label = new JLabel(viewModel.get("NoEvents"));
            parent.add(label);
        }
        List<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            c.gridy = i;

            String eventRawID = inputs.get(i);

            JButton eventPreview = new JButton(eventController.getEventName(eventRawID));
            eventPreview.addActionListener(e -> eventController.displayEvent(eventRawID));

            parent.add(eventPreview, c);
            buttons.add(eventPreview);
        }

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);

        this.add(panel, c);
    }
}
