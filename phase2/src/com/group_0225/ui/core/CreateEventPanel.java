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

        buildTitle(c, "Create Event");

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField nameField = addTextField(c,bottomPane,1, "Event Name:","");
        TextField startDateField = addTextField(c,bottomPane,2, "Enter Start Date:","");
        TextField endDateField = addTextField(c,bottomPane,3, "Enter End Date:","");

        Button goBack = addButton(c, bottomPane, 6, "Return");
        Button create = addButton(c, bottomPane, 5, "Create");

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.mainMenu();
            }
        });


        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
