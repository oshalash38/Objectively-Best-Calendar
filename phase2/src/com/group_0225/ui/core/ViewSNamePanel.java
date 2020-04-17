package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewSNamePanel extends CalendarLayoutPanel {
    public ViewSNamePanel(ControllerContainer controllerContainer) {
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

        buildTitle(constraints,inputs.get(0));

        for (int i = 1; i<inputs.size(); i+=5){
            addTextArea(inputs.get(i) + inputs.get(i+1) + inputs.get(i+2) + inputs.get(i+3),bottomPane, i+3, constraints);
        }
        constraints.gridy = 1;
        this.add(bottomPane,constraints);
    }
}
