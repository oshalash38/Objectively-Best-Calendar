package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter
 */
public class CreateSeriesFromEventsPanel extends CalendarLayoutPanel {
    private JLabel label;
    private JCheckBox checkBox;
    /*
    Display all events and allow user to select (click?) events to form a new series
    Need:
        -some form of id representation
        -can be a list of ids selected, a string of ids separated by commas (preferred), etc
        -let me know which one you have chosen so i can adjust controller accordingly
     */
    public CreateSeriesFromEventsPanel(ControllerContainer facade) {
        super(new GridBagLayout(), facade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        SeriesController sc = controllerContainer.getSeriesController();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());
        List<JCheckBox> checkBoxes = new ArrayList<>();
        buildTitle(constraints, viewModel.get("CreateSeriesFromEvents"));
        addLabel(constraints,bottomPane,0,viewModel.get("SeriesEventsInstructions"));
        for (int i = 0; i < inputs.size(); i+=5) {

            checkBox = addCheckBox(inputs.get(i), bottomPane, i+1, constraints);
            addLabel(constraints,bottomPane, i+2, inputs.get(i+1));
            addLabel(constraints,bottomPane,i+3, inputs.get(i+2));
            addLabel(constraints,bottomPane, i+4, inputs.get(i+3));
            addLabel(constraints,bottomPane, i+4,"");

            checkBoxes.add(checkBox);
            //checkBoxes.add();
        }
        Button createSeries = addButton(constraints, bottomPane, inputs.size() + 1, viewModel.get("CREATESERIES"));
        createSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO finish this!
            }
        });
        constraints.gridy = 1;
        this.add(bottomPane, constraints);


    }

}

