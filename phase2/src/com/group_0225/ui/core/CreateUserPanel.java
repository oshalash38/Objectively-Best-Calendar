package com.group_0225.ui.core;

import javax.swing.*;
import java.awt.*;

public class CreateUserPanel extends JPanel {

    public CreateUserPanel(){
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;

        //TODO Username, Password, Repeat Password, Error Dialog
        this.add(new Label("New Username:"), c);
        this.add(new TextField(), c);

        c.weighty = 1;
        c.gridy = 1;
        this.add(new Label("New Password:"), c);
        this.add(new TextField(), c);

        c.gridy = 2;
        this.add(new Label("Retype Password:"), c);
        this.add(new TextField(), c);

        c.gridy = 3;
        this.add(new Button("Submit"), c);
    }

}
