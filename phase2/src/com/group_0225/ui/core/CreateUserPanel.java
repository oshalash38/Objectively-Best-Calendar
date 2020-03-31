package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateUserPanel extends CalendarLayoutPanel {

    public CreateUserPanel(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }


    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, "Create New User");

        //Creates bottom portion of the panel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField newUsername = addTextField(c,bottomPane,1, "Username:", inputs.get(0));
        TextField newPassword = addTextField(c,bottomPane,2, "Password:","");
        TextField repeatNewPassword = addTextField(c,bottomPane,3, "Repeat Password:","");

        newPassword.setEchoChar('*');
        repeatNewPassword.setEchoChar('*');

        Button createNewUserButton = addButton(c, bottomPane, 4, "login");
        Button returnButton = addButton(c, bottomPane, 5, "return");

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.createNewUser(newUsername.getText(), newPassword.getText(), repeatNewPassword.getText());
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.startUp();
            }
        });


        c.gridy = 6;
        this.add(new Label(inputs.get(1)), c);

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
