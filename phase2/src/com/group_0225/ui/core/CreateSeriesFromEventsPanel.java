package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This panel allows the user to create a series from a list of events
 * @author Peter
 */
public class CreateSeriesFromEventsPanel extends CalendarLayoutPanel {
    private Map<JCheckBox,String> map;
    private JCheckBox checkBox;
    private List<JCheckBox> checkBoxes;
    /*
    Display all events and allow user to select (click?) events to form a new series
    Need:
        -some form of id representation
        -can be a list of ids selected, a string of ids separated by commas (preferred), etc
        -let me know which one you have chosen so i can adjust controller accordingly
     */

    /**
     * Creates a new CreateSeriesFromEventsPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CreateSeriesFromEventsPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
        map = new HashMap<>();
        checkBoxes = new ArrayList<>();
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        SeriesController sc = controllerContainer.getSeriesController();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());
        buildTitle(constraints, viewModel.get("CreateSeriesFromEvents"));
        TextField seriesName = addTextField(constraints,bottomPane,0,viewModel.get("seriesname"),"");
        addLabel(constraints,bottomPane,1,viewModel.get("SeriesEventsInstructions"));

        for (int i = 1; i < inputs.size(); i+=5) {

            checkBox = addCheckBox(inputs.get(i), bottomPane, i+2, constraints);
            addLabel(constraints,bottomPane, i+3, inputs.get(i+1));
            addLabel(constraints,bottomPane,i+4, inputs.get(i+2));
            addLabel(constraints,bottomPane, i+5, inputs.get(i+3));
            addLabel(constraints,bottomPane, i+6,"");
            map.put(checkBox,inputs.get(i+4));
            checkBoxes.add(checkBox);
        }
        Button createSeries = addButton(constraints, bottomPane, inputs.size() + 3, viewModel.get("CREATESERIES"));
        createSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> ids = new ArrayList<>();
                for (JCheckBox checkBox: checkBoxes){
                    if (checkBox.isSelected()) {
                        ids.add(map.get(checkBox));
                    }
                }
                sc.createSeriesFromEvents(seriesName.getText(),ids);

            }

        });
        addLabel(constraints,bottomPane,inputs.size()+4,inputs.get(0));
        constraints.gridy = 1;
        this.add(bottomPane, constraints);


    }

}

