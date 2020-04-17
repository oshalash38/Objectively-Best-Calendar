package com.group_0225.ui.core;

import com.group_0225.controller.*;
import com.group_0225.entities.Status;
import com.group_0225.ui.common.util.ViewModelBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CalendarToolBar extends JMenuBar {

    private LoginController loginController;
    private EventController eventController;
    private MessagingController messagingController;
    private SeriesController seriesController;
    private UsersCalendarController usersCalendarController;
    private CalendarGridController calendarGridController;
    private LocalTimeController localTimeController;
    private MemoController memoController;
    private AlertController alertController;

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
        this.usersCalendarController = controllerContainer.getUsersCalendarController();
        this.calendarGridController = controllerContainer.getCalendarGridController();
        this.localTimeController = controllerContainer.getLocalTimeController();
        this.memoController = controllerContainer.getMemoController();
        this.alertController = controllerContainer.getAlertController();

        toolBarBuilder();
    }


    public void loadCalendars(List<String> calendars){
        JMenu subCalendars = new JMenu(viewModel.get("TOOLBARUserChangeCalendarString"));
        List<JMenuItem> calendarItems = buildJMenuItems(subCalendars, false, calendars);
        userSettings.remove(0);
        userSettings.insert(subCalendars, 0);

        for(JMenuItem menu: calendarItems){
            menu.addActionListener(e -> usersCalendarController.changeCalendar(menu.getText(), calendarGridController));
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
        subMenus.get(0).addActionListener(e -> localTimeController.backToThePresent(calendarGridController));
        //Activate time travel
        subMenus.get(1).addActionListener(e -> localTimeController.pushChangeTimePanel());
    }

    private void buildUserSettingsMenu(){

        List<JMenuItem> subMenus = buildJMenuItems(userSettings, true,
                Arrays.asList("TOOLBARUserAddNewCalendarString", "TOOLBARUserLogoutString"));

        JMenu subCalendars = new JMenu(viewModel.get("TOOLBARUserChangeCalendarString"));
        userSettings.add(subCalendars, 0);

        //Add new Calendar
        subMenus.get(0).addActionListener(e -> usersCalendarController.pushAddNewCalendar());

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
        subMenus.get(0).addActionListener(e -> eventController.viewEventByStatus(Status.CURRENT));
        //Past events
        subMenus.get(1).addActionListener(e -> eventController.viewEventByStatus(Status.PAST));
        //Future events
        subMenus.get(2).addActionListener(e -> eventController.viewEventByStatus(Status.UPCOMING));
        //Date threshold
        subMenus.get(3).addActionListener(e -> eventController.viewEventByDateThreshold());
        //Memo
        subMenus.get(4).addActionListener(e -> memoController.pushDisplayMemos());
        //Tag
        subMenus.get(5).addActionListener(e -> {});
        //Name
        subMenus.get(6).addActionListener(e -> {});
        //Series
        subMenus.get(7).addActionListener(e -> seriesController.viewSNameChoiceScreen());


    }

    private void buildCreateMenu() {
        List<JMenuItem> subMenus = buildJMenuItems(createMenu, true,
                Arrays.asList("TOOLBARCreateMenuEventString", "TOOLBARCreateMenuAlertString",
                        "TOOLBARCreateMenuMemoString", "TOOLBARCreateMenuSeriesString"));

        //Create event
        subMenus.get(0).addActionListener(e -> eventController.pushCreateEvent());
        //Create Alert
        subMenus.get(1).addActionListener(e -> alertController.pushCreateNewAlert());
        //Create Memo
        subMenus.get(2).addActionListener(e -> memoController.pushCreateMemo());
        //Create Series
        subMenus.get(3).addActionListener(e -> seriesController.createSeriesChoiceScreen());

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


}
