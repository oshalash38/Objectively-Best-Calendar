package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel informs the user that no series have been created
 */
public class NoSeriesPanel extends CalendarLayoutPanel {
    /**
     * Creates a NoSeriesPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public NoSeriesPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        SeriesController sc = controllerContainer.getSeriesController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());
        bottomPane.setBackground(Color.darkGray);
        setBackground(Color.darkGray);

        addLabel(constraints, bottomPane, 2, viewModel.get("You have no series."));

        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
