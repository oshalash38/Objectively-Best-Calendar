package com.group_0225.ui;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.common.util.GUIBuilder;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.CalendarFrame;
import com.group_0225.ui.core.CalendarToolBar;

import javax.swing.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * This class observes the UI Presenter and is responsible for displaying all panels in the Gui
 */
public class UIManager implements Observer {


    private JPanel currPanel;
    private JDialog currDialog;
    private Map<String,CalendarLayoutPanel> panels;
    private CalendarFrame calendarFrame;
    private CalendarToolBar calendarToolBar;
    private CalendarData calendarData;
    private ControllerContainer controllerContainer;

    /**
     * Constructs a UIManager instance
     * @param controllerContainer a ControllerContainer instance
     * @param calendarData a CalendarData instance
     * @param calendarFrame a CalendarFrame
     */
    public UIManager(ControllerContainer controllerContainer, CalendarData calendarData, CalendarFrame calendarFrame){
        this.calendarToolBar = new CalendarToolBar(controllerContainer);
        this.calendarFrame = calendarFrame;
        this.calendarData = calendarData;
        this.controllerContainer = controllerContainer;

        calendarFrame.setJMenuBar(calendarToolBar);

        GUIBuilder guiBuilder = new GUIBuilder();
        panels = guiBuilder.buildPanels(controllerContainer);
    }

    /**
     * Runs the program on startup
     */
    public void run(){
        controllerContainer.getLoginController().startUp();
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        UIUpdateInfo info = (UIUpdateInfo) arg;

        switch (info.getRecipient()) {
            case "panel":
                displayPanel(info);
                break;
            case "dialog":
                displayDialog(info, false);
                break;
            case "scrollable":
                displayDialog(info, true);
                break;
            case "toolbar":
                updateToolbar(info);
        }
    }

    private void displayDialog(UIUpdateInfo info, boolean scrollable){
        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        JDialog dialog = new JDialog();
        currP.updatePanel(info.getData());
        if(scrollable){
            JScrollPane scrollPane = new JScrollPane(currP);
            dialog.add(scrollPane);
        }
        else {
            dialog.add(currP);
        }
        dialog.setSize(500,500);
        dialog.setVisible(true);
        if (currDialog != null){
            currDialog.dispose();
        }
        currDialog = dialog;
    }

    private void displayPanel(UIUpdateInfo info) {
        CalendarLayoutPanel currP = panels.get(info.getPanelKey());
        currP.updatePanel(info.getData());

        if(calendarData.getCurrUser() == null){
            calendarToolBar.setVisible(false);
        }
        else {
            calendarToolBar.setVisible(true);
        }
        if(currPanel != null)
            calendarFrame.remove(currPanel);
        currPanel = currP;
        calendarFrame.add(currP);
        calendarFrame.revalidate();
    }


    private void updateToolbar(UIUpdateInfo info){
        calendarToolBar.loadCalendars(info.getData());
    }
}
