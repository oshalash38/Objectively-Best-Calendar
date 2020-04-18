package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel allows the user to view certain events based on a date threshold
 */
public class DateThresholdPanel extends CalendarLayoutPanel {
    /**
     * Creates a new DateThresholdPanel
     * @param controllerContainer a ControllerContainer instance
     */
    public DateThresholdPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }
    @Override
    protected void buildPanel(List<String> inputs) {

        EventController eventController = controllerContainer.getEventsController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("GET_EVENTS_BY_THRESHOLD"));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(c, bottomPane, 2, viewModel.get("EnterStartDateString"));
        JDatePickerImpl startDateField = addCalendarPicker(c, bottomPane, 3);
        addLabel(c, bottomPane, 4, viewModel.get("EnterStartTimeString"));
        JSpinner startTimeSpinner = addTimeSpinner(c, bottomPane, 5);
        addLabel(c, bottomPane, 6, viewModel.get("EnterEndDateString"));
        JDatePickerImpl endDateField = addCalendarPicker(c, bottomPane, 7);
        addLabel(c, bottomPane, 8, viewModel.get("EnterEndTimeString"));
        JSpinner endTimeSpinner = addTimeSpinner(c, bottomPane, 9);
        Button find = addButton(c, bottomPane, 10, viewModel.get("Find"));

        if (inputs != null && inputs.size() > 0) {
            if (inputs.get(0).equals("Error1")){
                addLabel(c, bottomPane, 12, viewModel.get("EventError1"));
            } else if (inputs.get(0).equals("Error2")){
                addLabel(c, bottomPane, 12, viewModel.get("EventError2"));
            }
        }

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> input = new ArrayList<>();
                try{
                    input.add(startDateField.getJFormattedTextField().getText());
                    input.add(endDateField.getJFormattedTextField().getText());
                    input.add(startTimeSpinner.getValue().toString().substring(11,19));
                    input.add(endTimeSpinner.getValue().toString().substring(11,19));
                    eventController.getEventsByDateThreshold(input);
                } catch (StringIndexOutOfBoundsException ex){
                    List<String> errList = new ArrayList<>();
                    errList.add("Error1");
                    eventController.getEventsByDateThreshold(errList);
                }
            }
        });

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
