package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerFacade;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class CalendarLayoutPanel extends JPanel {

    protected ControllerFacade controllerFacade;

    public CalendarLayoutPanel(LayoutManager2 layoutManager2, ControllerFacade controllerFacade) {
        super(layoutManager2);

        this.controllerFacade = controllerFacade;
    }

    public void updatePanel(List<String> inputs) {
        this.removeAll();
        this.buildPanel(inputs);
    }

    protected abstract void buildPanel(List<String> inputs);
}
