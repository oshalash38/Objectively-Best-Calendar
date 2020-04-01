package com.group_0225.ui.core;

import com.group_0225.GUIBuilder;
import com.group_0225.PanelInfo;
import com.group_0225.UIPresenter;
import com.group_0225.controller.ControllerFacade;
import com.group_0225.CalendarData;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.util.*;

public class CalendarFrame extends JFrame implements Observer {

    private UIPresenter presenter;
    private GUIBuilder guiBuilder;
    private Map<String,CalendarLayoutPanel> panels;
    JPanel currPanel;

    public CalendarFrame() {
        super();
        guiBuilder = new GUIBuilder();
    }

    public void run(UIPresenter presenter, CalendarData calendarData, ControllerFacade controllerFacade){
        this.presenter = presenter;
        panels = guiBuilder.buildPanels(presenter, calendarData, controllerFacade);
        this.displayCalendarPanel(new PanelInfo("StartupPanel", Arrays.asList("", "")));
        this.setSize(800, 600);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(new CalendarToolBar(controllerFacade));
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

        if(info.isDialog()) {
            JDialog dialog = new JDialog();
            dialog.add(currP);
            dialog.setVisible(true);
        } else {
            if(currPanel != null)
                this.remove(currPanel);

            currPanel = currP;
            this.add(currP);
            pack();
            this.setSize(800, 600);
        }


    }

}
