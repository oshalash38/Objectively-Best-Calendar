package com.group_0225.ui.core;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.LoginController;
import com.group_0225.controller.UsersCalendarController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel is responsible for the displaying the UI on startup
 */
public class StartupPanel extends CalendarLayoutPanel {
    /**
     * Creates a new StartupPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public StartupPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        LoginController loginController = controllerContainer.getLoginController();
        CalendarGridController gridController = controllerContainer.getCalendarGridController();
        UsersCalendarController usersCalendarController = controllerContainer.getUsersCalendarController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("OBJECTIVELY BEST CALENDAR"));

        //Creates bottom portion of the panel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField usernameField = addTextField(c, bottomPane, 1, viewModel.get("Username:"), inputs.get(0));
        TextField passwordField = addTextField(c, bottomPane, 2, viewModel.get("Password:"), "");

        passwordField.setEchoChar('*');

        Button loginButton = addButton(c, bottomPane, 3, viewModel.get("Login"));
        Button createNewUserButton = addButton(c, bottomPane, 4, viewModel.get("Create New User"));

        loginButton.addActionListener(e -> loginController.login(usernameField.getText(),
                passwordField.getText(), gridController, usersCalendarController));

        createNewUserButton.addActionListener(e -> loginController.createNewUserScreen());


        c.gridy = 6;
        this.add(new Label(inputs.get(1)), c);

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }

}



