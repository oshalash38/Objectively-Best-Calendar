package com.group_0225.ui.core.tests;

import com.group_0225.ui.core.CalendarToolBar;
import com.group_0225.ui.core.CreateUserPanel;

import javax.swing.*;

class CreateUserPanelTest extends JFrame {
    public static void main(String[] args){
        CreateUserPanelTest c = new CreateUserPanelTest();
        c.run();
    }

    private void run(){

        this.add(new CreateUserPanel());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(new CalendarToolBar());

        this.setVisible(true);
    }
}