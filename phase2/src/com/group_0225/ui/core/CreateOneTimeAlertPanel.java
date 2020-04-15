package com.group_0225.ui.core;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOneTimeAlertPanel extends CalendarLayoutPanel {
    public CreateOneTimeAlertPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        AlertController alertController = controllerContainer.getAlertController();

        CreateAlertCommonPanel header = new CreateAlertCommonPanel(inputs.subList(1, inputs.size()), controllerContainer,
                "CreateOneTimeAlertTitleString");

        this.add(header);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        JPanel panel = new JPanel(new GridBagLayout());
        addLabel(c, panel, 0, viewModel.get("SelectDateString"));
        JDatePickerImpl dateSelection = addCalendarPicker(c, panel, 1);
        addLabel(c, panel, 2, viewModel.get("SelectTimeString"));
        JSpinner timeSelection = addTimeSpinner(c, panel, 3);

        Button confirmButton = addButton(c, panel, 4, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e-> {
            List<String> userInputs = new ArrayList<>();

            userInputs.add(header.getAlertNameUserInput());
            userInputs.add(header.getEventChoice());

            userInputs.add(dateSelection.getJFormattedTextField().getText());
            userInputs.add(timeSelection.getValue().toString().substring(11,19));
            alertController.createOneTimeAlert(userInputs);
        });

        c.gridy = 6;
        this.add(new Label(inputs.get(0)), c);


        c.gridy = 1;
        this.add(panel, c);
    }
}
