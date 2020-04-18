package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 * This panel collects the user's choice for which series name to filter their view of events
 */
public class ViewSNameChoicePanel extends CalendarLayoutPanel {
    /**
     * Creates a new ViewSNameChoicePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public ViewSNameChoicePanel(ControllerContainer controllerContainer) {
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

        buildTitle(constraints,viewModel.get("View Events by Series Name: "));

        addLabel(constraints,bottomPane,2,viewModel.get("Which series would you like to view?"));

        JComboBox<String> selection = addDropDown(inputs,bottomPane,4,constraints);

        Button view = addButton(constraints,bottomPane,6,viewModel.get("VIEW SERIES"));
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.pushViewSNameScreen(Collections.singletonList((String)selection.getSelectedItem()));
            }
        });
        constraints.gridy = 1;
        this.add(bottomPane,constraints);
    }
}
