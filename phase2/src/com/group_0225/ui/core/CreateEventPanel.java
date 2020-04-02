package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateEventPanel extends CalendarLayoutPanel {
    public CreateEventPanel(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("Create Event"));

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField nameField = addTextField(c,bottomPane,1, viewModel.get("Event Name:"),"");
        TextField startDateField = addTextField(c,bottomPane,2, viewModel.get("Enter Start Date:"),"");
        TextField endDateField = addTextField(c,bottomPane,3, viewModel.get("Enter End Date:"),"");

        Button create = addButton(c, bottomPane, 5, viewModel.get("Create"));
        Button goBack = addButton(c, bottomPane, 6, viewModel.get("Cancel"));


        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllerFacade.mainMenu();
            }
        });


        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
