package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel informs the user that they have no total tags
 */
public class NoTagsPanel extends CalendarLayoutPanel {
    /**
     * Creates a NoTagsPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public NoTagsPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(constraints, bottomPane, 2, viewModel.get("No tags:"));

        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
