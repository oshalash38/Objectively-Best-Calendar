package com.group_0225.ui.core;

import com.group_0225.controller.ControllerFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarToolBar extends JMenuBar {

    ControllerFacade controllerFacade;

    public CalendarToolBar(ControllerFacade controllerFacade) {
        super();
        this.controllerFacade = controllerFacade;

        JMenu createMenu = new JMenu("Create");
        JMenu viewMenu = new JMenu("View Events By:");
        JMenu userSettings = new JMenu("User");
        JMenu timeMachine = new JMenu("Time Machine");

        buildCreateMenu(createMenu);
        buildViewMenu(viewMenu);

        this.add(createMenu);
        this.add(viewMenu);





        JMenuItem speedUp = new JMenuItem("Speed Up Time");
        JMenuItem resumeNormalTime = new JMenuItem("Resume Normal Time");

    }

    private void buildViewMenu(JMenu viewMenu){
        JMenuItem currentEvents = new JMenuItem("Current events");
        JMenuItem pastEvents = new JMenuItem("Past events");
        JMenuItem futureEvents = new JMenuItem("Future events");
        JMenuItem dateThreshold = new JMenuItem("Date threshold");
        JMenuItem memo = new JMenuItem("Memo");
        JMenuItem tag = new JMenuItem("Tag");
        JMenuItem name = new JMenuItem("Name");
        JMenuItem series = new JMenuItem("Series");


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
                controllerFacade.viewEventByStatus(0);
            }
        });
        pastEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.viewEventByStatus(-1);
            }
        });
        futureEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.viewEventByStatus(1);
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
        JMenuItem createEvent = new JMenuItem("Create Event");
        JMenuItem createAlert = new JMenuItem("Create Alert");
        JMenuItem createMemo = new JMenuItem("Create Memo");
        JMenuItem createSeries = new JMenuItem("Create Series");

        createMenu.add(createEvent);
        createMenu.add(createAlert);
        createMenu.add(createMemo);
        createMenu.add(createSeries);

        createEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerFacade.pushCreateEvent();
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
