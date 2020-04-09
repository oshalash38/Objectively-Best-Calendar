package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.Event;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
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

        if (inputs.size() == 0){
            JLabel label = new JLabel(viewModel.get("NoEvents"));
            parent.add(label);
        }
        List<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            c.gridy = i;
            JButton eventPreview = new JButton(inputs.get(i));
            parent.add(eventPreview, c);
            buttons.add(eventPreview);
        }

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);

        for (JButton button : buttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }

        this.add(panel, c);
    }
}
