package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel displays all the events associated with a particular tag
 */
public class ViewByTagPanel extends CalendarLayoutPanel {
    /**
     * Creates a new ViewByTagPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public ViewByTagPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController eventManager = controllerContainer.getEventsController();
        SeriesController sc = controllerContainer.getSeriesController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        String title = "Events with tag: " + inputs.get(0);
        buildTitle(constraints,title);

        for (int i = 1; i<inputs.size();i++){
            addTextArea(inputs.get(i),bottomPane,i+2,constraints);
        }


        constraints.gridy = 1;
        this.add(bottomPane,constraints);
    }
}
