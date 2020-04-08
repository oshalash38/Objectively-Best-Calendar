package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.ViewModelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CalendarToolBar extends JMenuBar {

    private ControllerContainer controllerContainer;
    private EventController eventController;
    private Map<String, String> viewModel;


    public CalendarToolBar(ControllerContainer controllerContainer) {
        super();
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();

        this.controllerContainer = controllerContainer;
        eventController = controllerContainer.getEventsController();

        JMenu createMenu = new JMenu(viewModel.get("TOOLBARCreateMenuString"));
        JMenu viewMenu = new JMenu(viewModel.get("TOOLBARViewMenuString"));
        JMenu messagingMenu = new JMenu(viewModel.get("TOOLBARMessagingFunctionsString"));
        JMenu userSettings = new JMenu(viewModel.get("TOOLBARUserString"));
        JMenu timeMachine = new JMenu(viewModel.get("TOOLBARTimeMachineString"));

        buildMessagingMenu(messagingMenu);
        buildCreateMenu(createMenu);
        buildViewMenu(viewMenu);

        this.add(createMenu);
        this.add(viewMenu);







    }

    private void buildMessagingMenu(JMenu messagingMenu) {
        JMenuItem sendNewMessage = new JMenuItem(viewModel.get("TOOLBARMessagingFunctionsSendMessageString"));
        JMenuItem inbox = new JMenuItem(viewModel.get("TOOLBARMessagingFunctionsInboxString"));

        messagingMenu.add(sendNewMessage);
        messagingMenu.add(inbox);

        sendNewMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        inbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void buildViewMenu(JMenu viewMenu){

        JMenuItem currentEvents = new JMenuItem(viewModel.get("TOOLBARViewMenuCurrentEventsString"));
        JMenuItem pastEvents = new JMenuItem(viewModel.get("TOOLBARViewMenuPastEventsString"));
        JMenuItem futureEvents = new JMenuItem(viewModel.get("TOOLBARViewMenuFutureEventsString"));
        JMenuItem dateThreshold = new JMenuItem(viewModel.get("TOOLBARViewMenuDateThresholdString"));
        JMenuItem memo = new JMenuItem(viewModel.get("TOOLBARViewMenuMemoString"));
        JMenuItem tag = new JMenuItem(viewModel.get("TOOLBARViewMenuTagString"));
        JMenuItem name = new JMenuItem(viewModel.get("TOOLBARViewMenuNameString"));
        JMenuItem series = new JMenuItem(viewModel.get("TOOLBARViewMenuSeriesString"));


        viewMenu.add(currentEvents);
        viewMenu.add(pastEvents);
        viewMenu.add(futureEvents);
        viewMenu.add(dateThreshold);
        viewMenu.add(memo);
        viewMenu.add(tag);
        viewMenu.add(name);
        viewMenu.add(series);

        currentEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventController.viewEventByStatus(0);
            }
        });
        pastEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventController.viewEventByStatus(-1);
            }
        });
        futureEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventController.viewEventByStatus(1);
            }
        });
        dateThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        memo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tag.addActionListener(new ActionListener() {
            private ActionEvent e;
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        series.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    private void buildCreateMenu(JMenu createMenu) {
        JMenuItem createEvent = new JMenuItem(viewModel.get("TOOLBARCreateMenuEventString"));
        JMenuItem createAlert = new JMenuItem(viewModel.get("TOOLBARCreateMenuAlertString"));
        JMenuItem createMemo = new JMenuItem(viewModel.get("TOOLBARCreateMenuMemoString"));
        JMenuItem createSeries = new JMenuItem(viewModel.get("TOOLBARCreateMenuSeriesString"));

        createMenu.add(createEvent);
        createMenu.add(createAlert);
        createMenu.add(createMemo);
        createMenu.add(createSeries);

        createEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventController.pushCreateEvent();
            }
        });

        createAlert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllerFacade.pushCreateAlert();
            }
        });

        createMemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllerFacade.pushCreateMemo();
            }
        });

        createSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controllerFacade.pushCreateSeries();
            }
        });

    }
}
