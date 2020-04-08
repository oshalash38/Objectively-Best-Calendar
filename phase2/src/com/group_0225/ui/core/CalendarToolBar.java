package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.controller.LoginController;
import com.group_0225.controller.MessagingController;
import com.group_0225.ui.common.calendar.ViewModelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CalendarToolBar extends JMenuBar {

    private LoginController loginController;
    private EventController eventController;
    private MessagingController messagingController;
    private Map<String, String> viewModel;


    public CalendarToolBar(ControllerContainer controllerContainer) {
        super();
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();

        this.eventController = controllerContainer.getEventsController();
        this.messagingController = controllerContainer.getMessagingController();
        this.loginController = controllerContainer.getLoginController();

        JMenu createMenu = new JMenu(viewModel.get("TOOLBARCreateMenuString"));
        JMenu viewMenu = new JMenu(viewModel.get("TOOLBARViewMenuString"));
        JMenu messagingMenu = new JMenu(viewModel.get("TOOLBARMessagingFunctionsString"));
        JMenu userSettings = new JMenu(viewModel.get("TOOLBARUserString"));
        JMenu timeMachine = new JMenu(viewModel.get("TOOLBARTimeMachineString"));

        buildCreateMenu(createMenu);
        buildViewMenu(viewMenu);
        buildMessagingMenu(messagingMenu);
        buildUserSettingsMenu(userSettings);
        buildTimeMachinesMenu(timeMachine);

        this.add(userSettings);
        this.add(createMenu);
        this.add(viewMenu);
        this.add(messagingMenu);
        this.add(timeMachine);

    }

    private void buildTimeMachinesMenu(JMenu timeMachine){
        List<JMenuItem> subMenus = buildJMenuItems(timeMachine,
                Arrays.asList("TOOLBARTimeMachineReturnToPresentString", "TOOLBARTimeMachineTimeTravelString"));

        //Back to the present
        subMenus.get(0).addActionListener(e -> messagingController.pushSendNewMessagePanel());
        //Activate time travel
        subMenus.get(1).addActionListener(e -> messagingController.pushInboxPanel());
    }

    private void buildUserSettingsMenu(JMenu userSettings){
        List<JMenuItem> subMenus = buildJMenuItems(userSettings,
                Arrays.asList("TOOLBARUserChangeCalendarString", "TOOLBARUserChangePasswordString", "TOOLBARUserLogoutString"));

        //Change Calendar
        subMenus.get(0).addActionListener(e -> {});
        //Change Password
        subMenus.get(1).addActionListener(e -> {});
        //Logout
        subMenus.get(2).addActionListener(e -> loginController.startUp());
    }

    private void buildMessagingMenu(JMenu messagingMenu) {
        List<JMenuItem> subMenus = buildJMenuItems(messagingMenu,
                Arrays.asList("TOOLBARMessagingFunctionsSendMessageString", "TOOLBARMessagingFunctionsInboxString"));

        //Send Message
        subMenus.get(0).addActionListener(e -> messagingController.pushSendNewMessagePanel());
        //Inbox
        subMenus.get(1).addActionListener(e -> messagingController.pushInboxPanel());

    }

    private void buildViewMenu(JMenu viewMenu){

        List<JMenuItem> subMenus = buildJMenuItems(viewMenu,
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
        subMenus.get(3).addActionListener(e -> { });
        //Memo
        subMenus.get(4).addActionListener(e -> {});
        //Tag
        subMenus.get(5).addActionListener(e -> {});
        //Name
        subMenus.get(6).addActionListener(e -> {});
        //Series
        subMenus.get(7).addActionListener(e -> {});


    }

    private void buildCreateMenu(JMenu createMenu) {
        List<JMenuItem> subMenus = buildJMenuItems(createMenu,
                Arrays.asList("TOOLBARCreateMenuEventString", "TOOLBARCreateMenuAlertString",
                        "TOOLBARCreateMenuMemoString", "TOOLBARCreateMenuSeriesString"));

        //Create event
        subMenus.get(0).addActionListener(e -> eventController.pushCreateEvent());
        //Create Alert
        subMenus.get(1).addActionListener(e -> { });
        //Create Memo
        subMenus.get(2).addActionListener(e -> {});
        //Create Series
        subMenus.get(3).addActionListener(e -> { });

    }

    private List<JMenuItem> buildJMenuItems(JMenu menu, List<String> subMenus){
        List<JMenuItem> temp = new ArrayList<>();

        for(String str: subMenus){
            JMenuItem tempMenuItem = new JMenuItem(viewModel.get(str));
            menu.add(tempMenuItem);
            temp.add(tempMenuItem);
        }
        return temp;
    }
}
