package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panels allows the user to change the frequency of an alert
 */
public class AlertChangeFrequencyPanel extends CalendarLayoutPanel {
    /**
     * Creates a new AlertChangeFrequencyPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public AlertChangeFrequencyPanel(ControllerContainer controllerContainer) {
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

        buildTitle(c, viewModel.get("ChangeAlertFrequencyString"));

        JPanel panel = new JPanel(new GridBagLayout());

        addLabel(c, panel, 0, viewModel.get("SelectTimeFrequencyString"));
        TextField days = addTextField(c, panel, 1, viewModel.get("DaysFrequencyString"),"0");
        TextField hours = addTextField(c, panel, 2, viewModel.get("HourFrequencyString"),"0");
        TextField minutes = addTextField(c, panel, 3, viewModel.get("MinuteFrequencyString"),"0");

        Button confirmButton = addButton(c, panel, 4, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e->{
            List<String> userInputs = new ArrayList<>();

            userInputs.add(days.getText());
            userInputs.add(hours.getText());
            userInputs.add(minutes.getText());
            alertController.editFrequency(userInputs);
        });

        c.gridy = 6;
        this.add(new Label(inputs.get(0)), c);

        c.gridy = 1;
        this.add(panel,c);

    }
}
