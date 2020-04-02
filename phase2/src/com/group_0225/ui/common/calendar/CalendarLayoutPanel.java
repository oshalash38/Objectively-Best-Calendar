package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerFacade;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CalendarLayoutPanel extends JPanel {

    protected ControllerFacade controllerFacade;
    protected Map<String, String> viewModel;

    public CalendarLayoutPanel(LayoutManager2 layoutManager2, ControllerFacade controllerFacade) {
        super(layoutManager2);
        this.controllerFacade = controllerFacade;
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();
    }

    public void updatePanel(List<String> inputs) {
        this.removeAll();
        this.buildPanel(inputs);
    }

    protected Button addButton(GridBagConstraints c, JPanel bottomPane, int yPosition, String label){
        Button button = new Button(label);
        c.gridy = yPosition;
        bottomPane.add(button, c);
        return button;
    }

    protected void buildTitle(GridBagConstraints c, String text){
        Label titleLabel = new Label(text);
        titleLabel.setAlignment(Label.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        this.add(titleLabel, c);
    }

    protected TextField addTextField(GridBagConstraints c, JPanel bottomPane, int yPosition, String label, String text){
        c.fill = GridBagConstraints.NONE;
        c.gridy = yPosition;
        JPanel UserText = new JPanel();
        TextField field = new TextField(text);
        field.setColumns(30);
        UserText.add(new Label(label));
        UserText.add(field);
        bottomPane.add(UserText, c);
        return field;
    }


    protected abstract void buildPanel(List<String> inputs);
}
