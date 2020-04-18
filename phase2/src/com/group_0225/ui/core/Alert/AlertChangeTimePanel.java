package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel gives the user the option of changing the time of an alert
 */
public class AlertChangeTimePanel extends CalendarLayoutPanel {
    /**
     * Creates a new AlertChangeTimePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public AlertChangeTimePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    /**
     * Builds this CalendarLayoutPanel
     * @param inputs the inputs to display
     */
    @Override
    protected void buildPanel(List<String> inputs) {
        AlertController alertController = controllerContainer.getAlertController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;


        buildTitle(c, viewModel.get("ChangeAlertDateAndTimeString"));

        JPanel panel = new JPanel(new GridBagLayout());
        addLabel(c, panel, 0, viewModel.get("SelectDateString"));
        JDatePickerImpl dateSelection = addCalendarPicker(c, panel, 1);
        addLabel(c, panel, 2, viewModel.get("SelectTimeString"));
        JSpinner timeSelection = addTimeSpinner(c, panel, 3);


        Button confirmButton = addButton(c, panel, 4, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e-> {
            List<String> userInputs = new ArrayList<>();

            userInputs.add(dateSelection.getJFormattedTextField().getText());
            userInputs.add(timeSelection.getValue().toString().substring(11,19));
            alertController.editTime(userInputs, controllerContainer.getCalendarGridController());
        });

        c.gridy = 5;
        this.add(new Label(inputs.get(0)), c);

        c.gridy = 1;
        this.add(panel,c);
    }
}
