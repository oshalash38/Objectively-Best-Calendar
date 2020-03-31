package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class StartupPanel extends CalendarLayoutPanel {

    public StartupPanel(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, "OBJECTIVELY BEST CALENDAR");

        //Creates bottom portion of the panel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField usernameField = addTextField(c,bottomPane,1, "Username:","");
        TextField passwordField = addTextField(c,bottomPane,2, "Password:","");

        passwordField.setEchoChar('*');

        Button loginButton = addButton(c, bottomPane, 3, "login");
        Button createNewUserButton = addButton(c, bottomPane, 4, "create new user");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controllerFacade.login(usernameField.getText(), passwordField.getText());
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.createNewUser();
            }
        });

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }

}
