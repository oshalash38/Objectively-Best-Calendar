package com.group_0225.ui.core.Alert;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import java.awt.*;
import java.util.List;

/**
 * This class displays an Alert
 */
public class AlertPanel extends CalendarLayoutPanel {
    private int index;
    private int eventID;

    /**
     * Creates a new AlertPanel instances
     * @param controllerContainer a ControllerContainer instances
     * @param index the alertIndex
     * @param eventID the id of the event for this alert
     */
    public AlertPanel(ControllerContainer controllerContainer, int index, int eventID) {
        super(controllerContainer);
        this.index = index;
        this.eventID = eventID;
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

        addLabel(c,this, 1, inputs.get(0));
        addLabel(c,this, 2, inputs.get(1));
        addLabel(c,this, 3, inputs.get(2));

        Button editButton = addButton(c, this, 4, viewModel.get("EditAlertButton"));
        Button deleteButton = addButton(c, this, 4, viewModel.get("DeleteAlertButton"));

        editButton.addActionListener(e -> alertController.pushEditAlert(index, eventID));
        deleteButton.addActionListener(e -> alertController.deleteAlert(index, eventID, controllerContainer.getCalendarGridController()));
    }
}
