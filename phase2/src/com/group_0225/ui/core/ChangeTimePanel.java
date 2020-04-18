package com.group_0225.ui.core;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.LocalTimeController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel allows the user to change the time and date for the purpose of time travel
 */
public class ChangeTimePanel extends CalendarLayoutPanel {
    /**
     * Creates a ChangeTimePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public ChangeTimePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        LocalTimeController localTimeController = controllerContainer.getLocalTimeController();
        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("ChangeTimeTitleString"));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(c, bottomPane, 1, viewModel.get("SelectDateString"));
        JDatePickerImpl dateSelection = addCalendarPicker(c, bottomPane, 2);
        addLabel(c, bottomPane, 3, viewModel.get("SelectTimeString"));
        JSpinner timeSelection = addTimeSpinner(c, bottomPane, 4);

        Button confirmButton = addButton(c, bottomPane, 5, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e -> {
            List<String> userInputs = new ArrayList<>();

            userInputs.add(dateSelection.getJFormattedTextField().getText());
            userInputs.add(timeSelection.getValue().toString().substring(11,19));
            localTimeController.changeTime(userInputs, controllerContainer.getCalendarGridController());
            gridController.displayGrid();
        });


        c.gridy = 6;
        this.add(new Label(inputs.get(0)), c);


        c.gridy = 1;
        this.add(bottomPane, c);



    }
}
