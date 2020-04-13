package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventPanel extends CalendarLayoutPanel {
    public EventPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, inputs.get(0));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(c, bottomPane, 2, viewModel.get("Start Date: ") + inputs.get(1));
        addLabel(c, bottomPane, 4, viewModel.get("End Date: ") + inputs.get(2));
        addLabel(c, bottomPane, 6, viewModel.get("Start Time: ") + inputs.get(3));
        addLabel(c, bottomPane, 8, viewModel.get("End Time: ") + inputs.get(4));
        addLabel(c, bottomPane, 10, viewModel.get("Memos:"));
        addLabel(c, bottomPane, 12, viewModel.get("SeriesName:") + inputs.get(5));

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
