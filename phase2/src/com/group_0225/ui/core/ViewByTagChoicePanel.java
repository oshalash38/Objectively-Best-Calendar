package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewByTagChoicePanel extends CalendarLayoutPanel {
    public ViewByTagChoicePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController eventController = controllerContainer.getEventsController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        buildTitle(constraints, viewModel.get("View events by tag"));

        addLabel(constraints, bottomPane, 2, viewModel.get("Select a tag:"));

        JComboBox<String> selection = addDropDown(inputs, bottomPane, 4, constraints);

        Button view = addButton(constraints, bottomPane, 5, viewModel.get("VIEW EVENTS"));
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventController.viewEventsByTag((String) selection.getSelectedItem());
            }
        });


        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
