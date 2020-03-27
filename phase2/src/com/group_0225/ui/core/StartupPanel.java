package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;

        Label titleLabel = new Label("OBJECTIVELY BEST CALENDAR");
        titleLabel.setAlignment(Label.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));

        this.add(titleLabel, c);

        //Creates bottom portion of the panel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField usernameField = addTextField(c,bottomPane,1, "Username:");
        TextField passwordField = addTextField(c,bottomPane,2, "Password:");
        Button loginButton = addButton(c, bottomPane, 3, "login");
        Button createNewUserButton = addButton(c, bottomPane, 4, "create new user");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.login(usernameField.getText(), passwordField.getText());
            }
        });

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.createNewUser(); // <- Still needs to be implemented
            }
        });


        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }

    private Button addButton(GridBagConstraints c, JPanel bottomPane, int yPosition, String label){
        Button button = new Button(label);
        c.gridy = yPosition;
        bottomPane.add(button, c);
        return button;
    }

    private TextField addTextField(GridBagConstraints c, JPanel bottomPane, int yPosition, String label){
        c.fill = GridBagConstraints.NONE;
        c.gridy = yPosition;
        JPanel username = new JPanel();
        TextField usernameField = new TextField();
        usernameField.setColumns(30);
        username.add(new Label(label));
        username.add(usernameField);
        bottomPane.add(username, c);
        return usernameField;
    }
}
