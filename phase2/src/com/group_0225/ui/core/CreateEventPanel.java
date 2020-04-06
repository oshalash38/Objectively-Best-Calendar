package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

public class CreateEventPanel extends CalendarLayoutPanel {
    public CreateEventPanel(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("CreateEventString"));

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField nameField = addTextField(c,bottomPane,1, viewModel.get("EventNameString"),"");
        JLabel startDateFieldLabel = addLabel(c, bottomPane, 2, viewModel.get("EnterStartDateString"));
        JDatePickerImpl startDateField = addCalendarPicker(c, bottomPane, 3);
        JLabel startTimeFieldLabel = addLabel(c, bottomPane, 4, viewModel.get("EnterStartTimeString"));
        JSpinner startTimeSpinner = addTimeSpinner(c, bottomPane, 5);
        JLabel endDateFieldLabel = addLabel(c, bottomPane, 6, viewModel.get("EnterEndDateString"));
        JDatePickerImpl endDateField = addCalendarPicker(c, bottomPane, 7);
        JLabel endTimeFieldLabel = addLabel(c, bottomPane, 8, viewModel.get("EnterEndTimeString"));
        JSpinner endTimeSpinner = addTimeSpinner(c, bottomPane, 9);
        Button create = addButton(c, bottomPane, 10, viewModel.get("CreateString"));
        Button goBack = addButton(c, bottomPane, 11, viewModel.get("CancelString"));


        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllerFacade.mainMenu();
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(startDateField.getJFormattedTextField().getText());
                System.out.println(endDateField.getJFormattedTextField().getText());
                System.out.println(startTimeSpinner.getValue().toString().substring(11,19));
                System.out.println(endTimeSpinner.getValue().toString().substring(11,19));

            }
        });


        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
