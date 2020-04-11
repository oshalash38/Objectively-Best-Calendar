package com.group_0225.ui.core;

import com.group_0225.controller.*;
import com.group_0225.ui.common.calendar.ViewModelBuilder;
import com.group_0225.ui.common.util.UIUpdateInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CalendarToolBar extends JMenuBar implements Observer{

    private LoginController loginController;
    private EventController eventController;
    private MessagingController messagingController;
    private SeriesController seriesController;
    private Map<String, String> viewModel;

    private JMenu createMenu;
    private JMenu viewMenu;
    private JMenu messagingMenu;
    private JMenu userSettings;
    private JMenu timeMachine;



    public CalendarToolBar(ControllerContainer controllerContainer) {
        super();
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();

        this.eventController = controllerContainer.getEventsController();
        this.messagingController = controllerContainer.getMessagingController();
        this.loginController = controllerContainer.getLoginController();
        this.seriesController = controllerContainer.getSeriesController();

        toolBarBuilder();
    }


    private void loadCalendars(List<String> calendars){
        JMenu submenu = new JMenu(viewModel.get("TOOLBARUserChangeCalendarString"));
        List<JMenuItem> calendarItems = buildJMenuItems(submenu, false, calendars);
        userSettings.insert(submenu, 0);

        for(JMenuItem menu: calendarItems){
            menu.addActionListener(e -> eventController.changeCalendar(menu.getText()));
        }
    }

    private void toolBarBuilder(){
        this.createMenu = new JMenu(viewModel.get("TOOLBARCreateMenuString"));
        this.viewMenu = new JMenu(viewModel.get("TOOLBARViewMenuString"));
        this.messagingMenu = new JMenu(viewModel.get("TOOLBARMessagingFunctionsString"));
        this.userSettings = new JMenu(viewModel.get("TOOLBARUserString"));
        this.timeMachine = new JMenu(viewModel.get("TOOLBARTimeMachineString"));

        buildCreateMenu();
        buildViewMenu();
        buildMessagingMenu();
        buildUserSettingsMenu();
        buildTimeMachinesMenu();

        this.add(userSettings);
        this.add(createMenu);
        this.add(viewMenu);
        this.add(messagingMenu);
        this.add(timeMachine);
    }

    private void buildTimeMachinesMenu(){
        List<JMenuItem> subMenus = buildJMenuItems(timeMachine, true,
                Arrays.asList("TOOLBARTimeMachineReturnToPresentString", "TOOLBARTimeMachineTimeTravelString"));

        //Back to the present
        subMenus.get(0).addActionListener(e -> messagingController.pushSendNewMessagePanel());
        //Activate time travel
        subMenus.get(1).addActionListener(e -> messagingController.pushInboxPanel());
    }

    private void buildUserSettingsMenu(){

        List<JMenuItem> subMenus = buildJMenuItems(userSettings, true,
                Arrays.asList("TOOLBARUserAddNewCalendarString", "TOOLBARUserLogoutString"));


        //Add new Calendar
        subMenus.get(0).addActionListener(e -> {});

        //Logout
        subMenus.get(1).addActionListener(e -> loginController.startUp());
    }

    private void buildMessagingMenu() {
        List<JMenuItem> subMenus = buildJMenuItems(messagingMenu, true,
                Arrays.asList("TOOLBARMessagingFunctionsSendMessageString", "TOOLBARMessagingFunctionsInboxString"));

        //Send Message
        subMenus.get(0).addActionListener(e -> messagingController.pushSendNewMessagePanel());
        //Inbox
        subMenus.get(1).addActionListener(e -> messagingController.pushInboxPanel());

    }

    private void buildViewMenu(){

        List<JMenuItem> subMenus = buildJMenuItems(viewMenu, true,
                Arrays.asList("TOOLBARViewMenuCurrentEventsString", "TOOLBARViewMenuPastEventsString",
                        "TOOLBARViewMenuFutureEventsString", "TOOLBARViewMenuDateThresholdString",
                        "TOOLBARViewMenuMemoString","TOOLBARViewMenuTagString","TOOLBARViewMenuNameString",
                        "TOOLBARViewMenuSeriesString"));

        //Current events
        subMenus.get(0).addActionListener(e -> eventController.viewEventByStatus(0));
        //Past events
        subMenus.get(1).addActionListener(e -> eventController.viewEventByStatus(-1));
        //Future events
        subMenus.get(2).addActionListener(e -> eventController.viewEventByStatus(1));
        //Date threshold
        subMenus.get(3).addActionListener(e -> {});
        //Memo
        subMenus.get(4).addActionListener(e -> {});
        //Tag
        subMenus.get(5).addActionListener(e -> {});
        //Name
        subMenus.get(6).addActionListener(e -> {});
        //Series
        subMenus.get(7).addActionListener(e -> {});


    }

    private void buildCreateMenu() {
        List<JMenuItem> subMenus = buildJMenuItems(createMenu, true,
                Arrays.asList("TOOLBARCreateMenuEventString", "TOOLBARCreateMenuAlertString",
                        "TOOLBARCreateMenuMemoString", "TOOLBARCreateMenuSeriesString"));

        //Create event
        subMenus.get(0).addActionListener(e -> eventController.pushCreateEvent());
        //Create Alert
        subMenus.get(1).addActionListener(e -> { });
        //Create Memo
        subMenus.get(2).addActionListener(e -> {});
        //Create Series
        subMenus.get(3).addActionListener(e -> seriesController.createSeriesFromScratchScreen());

    }

    private List<JMenuItem> buildJMenuItems(JMenuItem menu,  boolean fromViewModel, List<String> subMenus){
        List<JMenuItem> temp = new ArrayList<>();

        for(String str: subMenus){
            JMenuItem tempMenuItem;
            if(fromViewModel) {
                tempMenuItem = new JMenuItem(viewModel.get(str));
            }
            else{
                 tempMenuItem = new JMenuItem(str);
            }
            menu.add(tempMenuItem);
            temp.add(tempMenuItem);
        }
        return temp;
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
        UIUpdateInfo info = (UIUpdateInfo)arg;
        if(info.getRecipient().equals("toolbar")){
            loadCalendars(info.getData());
        }
    }
}
