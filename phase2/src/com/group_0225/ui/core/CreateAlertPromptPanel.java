package com.group_0225.ui.core;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CreateAlertPromptPanel extends CalendarLayoutPanel {
    public CreateAlertPromptPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> outputs) {
        AlertController alertController = controllerContainer.getAlertController();
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;



        buildTitle(c, viewModel.get("CreateAlertTitleString"));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        c.gridy = 1;
        this.add(new Label(viewModel.get("RecurringAlertOrNot")), c);

        Button oneTime = addButton(c, bottomPane, 2, viewModel.get("OneTimeButtonString"));
        Button repeating = addButton(c, bottomPane, 3, viewModel.get("OneTimeButtonString"));


        oneTime.addActionListener( e -> {
            alertController.pushCreateOneTimeAlert();
        });
        repeating.addActionListener( e -> {
            alertController.pushCreateRepeatingAlert();
        });
    }
}
