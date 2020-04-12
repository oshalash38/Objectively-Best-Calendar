package com.group_0225.ui.core;

import com.group_0225.ui.common.util.GUIBuilder;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.*;

public class CalendarFrame extends JFrame implements Observer {

    private ControllerContainer controllerContainer;
    private UIPresenter presenter;
    private GUIBuilder guiBuilder;
    private Map<String,CalendarLayoutPanel> panels;
    private CalendarData calendarData;
    JDialog openDialog;
    JPanel currPanel;

    public CalendarFrame(UIPresenter presenter, CalendarData calendarData,
                         ControllerContainer controllerContainer, CalendarToolBar calendarToolBar) {
        super();

        guiBuilder = new GUIBuilder();

        //Logo from https://freeiconshop.com/icon/calendar-icon-glyph/
        File image = new File("phase2/src/com/group_0225/ui/core/Model/logo.png");
        System.out.println(image.getAbsolutePath());
        ImageIcon imageIcon = new ImageIcon(image.getAbsolutePath());
        this.setIconImage(imageIcon.getImage());

        this.controllerContainer = controllerContainer;
        this.calendarData = calendarData;
        this.setJMenuBar(calendarToolBar);
        panelBuilder(controllerContainer);
    }

    private void panelBuilder(ControllerContainer controllerContainer){
        panels = guiBuilder.buildPanels(presenter, calendarData, controllerContainer);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        UIUpdateInfo info = (UIUpdateInfo) arg;
        if(info.getRecipient().equals("panel")) {
            this.displayCalendarPanel(info);
        }
        else if(info.getRecipient().equals("dialog")){
            displayDialog(info);
        }
        else if (info.getRecipient().equals("scrollable")){
            scrollableDialog(info);
        }
    }

    private void scrollableDialog(UIUpdateInfo info){
        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getData());
        JScrollPane scrollPane = new JScrollPane(currP);
        JDialog dialog = new JDialog();
        dialog.setSize(500,500);
        dialog.add(scrollPane);
        dialog.setVisible(true);
        if (openDialog != null){
            openDialog.dispose();
        }
        openDialog = dialog;
    }
    private void displayDialog(UIUpdateInfo info) {
        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getData());

        JDialog dialog = new JDialog();
        dialog.setSize(500,500);
        dialog.add(currP);
        dialog.setVisible(true);
        if (openDialog != null){
            openDialog.dispose();
        }
        openDialog = dialog;
    }


    private void displayCalendarPanel(UIUpdateInfo info) {

        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getData());

        if(calendarData.getCurrUser() == null){
            this.getJMenuBar().setVisible(false);
        }
        else
            this.getJMenuBar().setVisible(true);

        if(currPanel != null)
            this.remove(currPanel);
        currPanel = currP;
        this.add(currP);
        this.revalidate();

    }

}
