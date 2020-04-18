package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.SeriesController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Peter
 * Collect info from user to make a new series from scratch
 */
public class CreateSeriesScratchPanel extends CalendarLayoutPanel {
    /*Need:
        series name
        an integer from 1-5 inclusive representing the frequency of each event in the series
            1: Hourly
            2: Daily
            3: Weekly
            4: Monthly
            5: Yearly
        the start time (idk if u can do this, but perhaps this could be its separate panel to save space; it was a separate view in phase1)
            Enter year:
            Enter month no: (1-12)
            Enter day of month: (1- 31)
            Enter hour of day (24 hr clock)
            Enter minute of hour (0-59)
        the duration of each event
            Enter number of days (0-6)
            Enter number of hours (24 hour clock)
            Enter number of minutes (0-59)
        the number of events in the series (min. 2)
     */

    /**
     * Creates a new CreateSeriesScratchPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CreateSeriesScratchPanel(ControllerContainer controllerContainer){
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


        //Dialog header
        buildTitle(constraints,viewModel.get("CreateSeriesFromScratch"));

        //series name
        TextField seriesName = addTextField(constraints,bottomPane,1,viewModel.get("seriesname"),"");

        //frequency label
        addLabel(constraints,bottomPane,2,viewModel.get("freq"));

        //frequency
        List<String> fSelections = new ArrayList<>();
        fSelections.add("1. Hourly");
        fSelections.add("2. Daily");
        fSelections.add("3: Weekly");
        fSelections.add("4: Monthly");
        fSelections.add("5: Yearly");
        JComboBox<String> frequency = addDropDown(fSelections,bottomPane,3,constraints);

        //numEvents
        addLabel(constraints, bottomPane, 4, viewModel.get("NE:"));
        JSpinner numEventsField = addNumEventsSpinner(constraints,bottomPane, 5);

        //start date
        addLabel(constraints,bottomPane, 6, viewModel.get("EnterStartDateString"));
        JDatePickerImpl startDateField = addCalendarPicker(constraints,bottomPane, 7);

        //start time
        addLabel(constraints, bottomPane, 8, viewModel.get("EnterStartTimeString"));
        JSpinner startTimeField = addTimeSpinner(constraints,bottomPane,9);

        //duration
        addLabel(constraints, bottomPane, 10, viewModel.get("EnterDurationSeriesString"));
        addLabel(constraints, bottomPane, 11, viewModel.get("Days:"));
        List<String> dSelections = new ArrayList<>();
        dSelections.add("0");
        dSelections.add("1");
        dSelections.add("2");
        dSelections.add("3");
        dSelections.add("4");
        dSelections.add("5");
        dSelections.add("6");
        JComboBox<String> dayField = addDropDown(dSelections,bottomPane, 12,constraints);

        addLabel(constraints,bottomPane, 13, viewModel.get("HMS:"));
        JSpinner durationField = addTimeSpinner(constraints,bottomPane, 14);


        Button create = addButton(constraints,bottomPane, 20, viewModel.get("CREATESERIES"));
        create.addActionListener(e -> {
            List<String> inputs1 = new ArrayList<>();
            //TODO: delete the following test print statements before submission

                inputs1.add(seriesName.getText());
                inputs1.add(((String) Objects.requireNonNull(frequency.getSelectedItem())).substring(0,1));
                inputs1.add(startDateField.getJFormattedTextField().getText());
                inputs1.add(startTimeField.getValue().toString().substring(11,19));
                inputs1.add(((String) Objects.requireNonNull(dayField.getSelectedItem())).substring(0,1));
                inputs1.add(durationField.getValue().toString().substring(11,19));
                inputs1.add(numEventsField.getValue().toString());
                sc.createSeriesFromScratch(inputs1, controllerContainer.getCalendarGridController());
        });
        addLabel(constraints,bottomPane,16,inputs.get(0));
        constraints.gridy = 1;
        this.add(bottomPane,constraints);

    }
}
