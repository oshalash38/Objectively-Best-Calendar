package com.group_0225.ui.core;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.LoginController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class StartupPanel extends CalendarLayoutPanel{// implements KeyListener {

    public StartupPanel(ControllerContainer controllerContainer) {
        super(new GridBagLayout(), controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        LoginController loginController = controllerContainer.getLoginController();
        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("OBJECTIVELY BEST CALENDAR"));

        //Creates bottom portion of the panel
        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField usernameField = addTextField(c,bottomPane,1, viewModel.get("Username:"), inputs.get(0));
        TextField passwordField = addTextField(c,bottomPane,2, viewModel.get("Password:"),"");

        passwordField.setEchoChar('*');

        Button loginButton = addButton(c, bottomPane, 3, viewModel.get("Login"));
        Button createNewUserButton = addButton(c, bottomPane, 4, viewModel.get("Create New User"));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginController.login(usernameField.getText(), passwordField.getText(), gridController);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.createNewUserScreen();
            }
        });



        c.gridy = 6;
        this.add(new Label(inputs.get(1)), c);

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);
    }






//    /**
//     * Got idea from https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
//     *
//     * Invoked when a key has been pressed.
//     * See the class description for {@link KeyEvent} for a definition of
//     * a key pressed event.
//     *
//     * @param e
//     */
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode()==KeyEvent.VK_ENTER){
//
//        }
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {}
//    @Override
//    public void keyReleased(KeyEvent e) {}
}
