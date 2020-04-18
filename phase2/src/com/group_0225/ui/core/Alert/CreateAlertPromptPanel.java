package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class allows the user to create an alert
 */
public class CreateAlertPromptPanel extends CalendarLayoutPanel {
    /**
     * Creates a CreateAlertPromptPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
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
        Button repeating = addButton(c, bottomPane, 3, viewModel.get("RepeatingButtonString"));


        oneTime.addActionListener( e -> {
            alertController.pushCreateOneTimeAlert();
        });
        repeating.addActionListener( e -> {
            alertController.pushCreateRepeatingAlert();
        });

        c.gridy = 2;
        this.add(bottomPane, c);
    }
}
