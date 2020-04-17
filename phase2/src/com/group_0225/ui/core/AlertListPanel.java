package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AlertListPanel extends CalendarLayoutPanel {
    public AlertListPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints c = new GridBagConstraints();
        int eventID;

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        JPanel panel = new JPanel(new GridBagLayout());
        buildTitle(c, viewModel.get("AlertString"));

        for(String str: inputs){
            System.out.println(str);
        }

        System.out.println(inputs.size());


        String error = inputs.get(0);

        try {
            eventID = Integer.parseInt(inputs.get(1));
        }catch (NumberFormatException e){eventID = 0;}

        buildTitle(c, viewModel.get(viewModel.get("AlertString")));

        inputs = inputs.subList(2, inputs.size());

        c.gridy = 1;
        for(int i = 0; i < inputs.size(); i += 3){
            AlertPanel alertPanel = new AlertPanel(controllerContainer, i/3, eventID);
            alertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            List<String> temp = inputs.subList(i, i+3);
            alertPanel.buildPanel(temp);
            panel.add(alertPanel, c);
            c.gridy++;
        }

        addLabel(c,panel, c.gridy, error);
        //Adds bottom pane
        c.gridy = 1;
        this.add(panel, c);
    }
}
