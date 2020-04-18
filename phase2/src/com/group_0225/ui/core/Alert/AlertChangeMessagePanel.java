package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel allows the user to change the message of an alert
 */
public class AlertChangeMessagePanel extends CalendarLayoutPanel {
    /**
     * Creates a new AlertChangeMessagePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public AlertChangeMessagePanel(ControllerContainer controllerContainer) {
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


        buildTitle(c, viewModel.get("ChangeAlertMessageString"));

        JPanel panel = new JPanel(new GridBagLayout());
        TextField message = addTextField(c,panel, 1, viewModel.get("AlertNamePromptString"), "");

        Button confirmButton = addButton(c, panel, 2, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e-> {
            List<String> userInputs = new ArrayList<>();
            userInputs.add(message.getText());
            alertController.editMessage(userInputs);
        });

        c.gridy = 3;
        this.add(new Label(inputs.get(0)), c);

        c.gridy = 1;
        this.add(panel,c);
    }
}
