package com.group_0225.ui.core;

import com.group_0225.ui.common.util.GUIBuilder;
import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.util.*;

public class CalendarFrame extends JFrame implements Observer {

    private UIPresenter presenter;
    private GUIBuilder guiBuilder;
    private Map<String,CalendarLayoutPanel> panels;
    private CalendarData calendarData;
    JPanel currPanel;

    public CalendarFrame(UIPresenter presenter, CalendarData calendarData, ControllerContainer controllerContainer) {
        super();
        guiBuilder = new GUIBuilder();
        this.calendarData = calendarData;
        panelBuilder(presenter, calendarData, controllerContainer);
    }

    private void panelBuilder(UIPresenter presenter, CalendarData calendarData, ControllerContainer controllerContainer){
        this.presenter = presenter;
        panels = guiBuilder.buildPanels(presenter, calendarData, controllerContainer);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(new CalendarToolBar(controllerContainer));
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        PanelInfo info = (PanelInfo) arg;
        this.displayCalendarPanel(info);
    }

    private void displayCalendarPanel(PanelInfo info) {

        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getPanelData());

        if(calendarData.getCurrUser() == null){
            this.getJMenuBar().setVisible(false);
        }
        else
            this.getJMenuBar().setVisible(true);

        if(info.isDialog()) {
            JDialog dialog = new JDialog();
            dialog.setSize(500,400);
            dialog.add(currP);
            dialog.setVisible(true);
        } else {
            if(currPanel != null)
                this.remove(currPanel);
            currPanel = currP;
            this.add(currP);
            this.revalidate();
        }


    }

}
