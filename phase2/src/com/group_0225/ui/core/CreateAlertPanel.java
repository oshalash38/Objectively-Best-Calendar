package com.group_0225.ui.core;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class CreateAlertPanel extends CalendarLayoutPanel {
    public CreateAlertPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        AlertController alertController = controllerContainer.getAlertController();
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;



        buildTitle(c, viewModel.get("CreateAlertTitleString"));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        c.gridy = 1;
        this.add(new Label(viewModel.get("RecurringAlertOrNot")), c);



    }
}
