package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import java.awt.*;
import java.util.List;

/**
 * This panel allows the user to edit alerts
 */
public class EditAlertButtonsPanel extends CalendarLayoutPanel {
    /**
     * Creates a new EditAlertButtonsPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public EditAlertButtonsPanel(ControllerContainer controllerContainer) {
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

        buildTitle(c, viewModel.get("EditAlertString"));
        c.gridy = 1;
        for(String str: inputs){
            Button temp = addButton(c,this,c.gridy, str);
            temp.addActionListener(e -> alertController.editAlert(str));
            c.gridy++;
        }
    }
}
