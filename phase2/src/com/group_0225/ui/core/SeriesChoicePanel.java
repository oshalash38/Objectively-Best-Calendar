package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This panel displays a choice between creating a series from scratch or from created events
 */
public class SeriesChoicePanel extends CalendarLayoutPanel {
    /**
     * Creates a SeriesChoicePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public SeriesChoicePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints constraints = new GridBagConstraints();
        SeriesController sc = controllerContainer.getSeriesController();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        //Dialog header
        buildTitle(constraints,viewModel.get("Create series exclamation"));
        addLabel(constraints,bottomPane,1,viewModel.get("SeriesDescription"));
        addLabel(constraints,bottomPane,2,viewModel.get("SeriesHow:"));
        Button fromScratch = addButton(constraints,bottomPane,3, viewModel.get("From scratch:"));
        fromScratch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.createSeriesFromScratchScreen();
            }
        });
        Button fromEvents = addButton(constraints,bottomPane,4,viewModel.get("From events:"));
        fromEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.createSeriesFromEventsScreen();
            }
        });
        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
