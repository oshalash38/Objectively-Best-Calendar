package com.group_0225.ui.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupPanel extends JPanel {

    public StartupPanel() {
        super(new GridBagLayout());

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

        //Creates the username area
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        JPanel username = new JPanel();
        TextField usernameField = new TextField();
        usernameField.setColumns(30);
        username.add(new Label("Username:"));
        username.add(usernameField);
        bottomPane.add(username, c);

        //Creates the password area
        c.gridy = 2;
        JPanel password = new JPanel();
        TextField passwordField = new TextField();
        passwordField.setColumns(30);
        password.add(new Label("Password:"));
        password.add(passwordField);
        bottomPane.add(password, c);

        //DUMMY THICC BUTTONS
        c.gridy = 3;
        Button loginButton = new Button("Login");
        loginButton.addActionListener(getLoginButtonListener());
        bottomPane.add(loginButton, c);
        c.gridy = 4;
        bottomPane.add(new Button("Create New User"), c);

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }

    private ActionListener getLoginButtonListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
