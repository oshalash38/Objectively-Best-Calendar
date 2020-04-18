package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This panel allows the user to choose which tag they would like to use to filter their view of events
 */
public class ViewByTagChoicePanel extends CalendarLayoutPanel {
    /**
     * Creates a new ViewByTagChoicePanel instance
     * @param controllerContainer a ControllerContainer
     */
    public ViewByTagChoicePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController eventController = controllerContainer.getEventsController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        //constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        buildTitle(constraints, viewModel.get("View events by tag"));

        addLabel(constraints, bottomPane, 1, viewModel.get("Select a tag:"));

        JComboBox<String> selection = addDropDown(inputs, bottomPane, 2, constraints);
        for (int i = 0; i <= inputs.size(); i++) {
            addLabel(constraints, bottomPane, i+4, "    ");
        }

        Button view = addButton(constraints, bottomPane, inputs.size()+5, viewModel.get("VIEW EVENTS"));
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
