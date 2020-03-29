package com.group_0225.ui.core;

import com.group_0225.GUIBuilder;
import com.group_0225.PanelInfo;
import com.group_0225.UIPresenter;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CalendarFrame extends JFrame implements Observer {

    private UIPresenter presenter;
    private GUIBuilder guiBuilder;
    private Map<String,CalendarLayoutPanel> panels;
    private JPanel currPanel;

    public CalendarFrame() {
        super();
        guiBuilder = new GUIBuilder();
    }

    public void run(UIPresenter presenter){
        this.presenter = presenter;
        panels = guiBuilder.buildPanels(presenter);
        this.displayCalendarPanel(new PanelInfo("StartupPanel", new ArrayList<>()));
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setJMenuBar(new CalendarToolBar());

        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        PanelInfo info = (PanelInfo) arg;
        this.displayCalendarPanel(info);
    }

    private void displayCalendarPanel(PanelInfo info) {
        if(currPanel != null)
            this.remove(currPanel);

        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getPanelData());

        this.add(currP);
        currPanel = currP;
        pack();

    }
}
