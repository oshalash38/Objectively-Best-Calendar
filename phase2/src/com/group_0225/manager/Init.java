package com.group_0225.manager;

import com.group_0225.DatabaseReader;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.TimingFactory;
import com.group_0225.entities.User;
import com.group_0225.ui.UIManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.core.CalendarFrame;
import com.group_0225.ui.core.CalendarToolBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class initializes all the classes needed for the program to run.
 *
 * @author Daniel Shoichet
 */

public class Init {

    public void run() throws IOException, ClassNotFoundException {
        TimingFactory timingFactory = new TimingFactory();
        CalendarFrame calendarFrame = new CalendarFrame();
        UIPresenter presenter = new UIPresenter();
        DatabaseReader databaseReader = new DatabaseReader();
        CalendarData calendarData = new CalendarData();

        calendarData.setLocalTime(timingFactory.createTiming(LocalDateTime.now()));
        populate(calendarData, databaseReader);

        ControllerContainer controllerContainer = new ControllerContainer(presenter, calendarData);
        UIManager uiManager = new UIManager(controllerContainer, calendarData, calendarFrame);
        presenter.addObserver(uiManager);


        // From https://stackoverflow.com/questions/2592207/how-to-improve-look-and-feel-of-java-swing-gui
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        calendarFrame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e the window event
             */
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    databaseReader.saveToFile("users.txt", calendarData.getUsers());
                    databaseReader.saveToFile("events.txt", calendarData.getEvents());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        uiManager.run();
    }

    private void populate(CalendarData calendarData, DatabaseReader databaseReader) throws IOException, ClassNotFoundException {

        Object users = databaseReader.readFile("users.txt");
        Object events = databaseReader.readFile("events.txt");
        if(users != null){
            System.out.println(((Map<String, User>)users).size());
            calendarData.setUsers((Map<String, User>)users);
        }
        if(events != null){
            System.out.println(((Map<Integer, Event>)events).size());
            calendarData.setEvents((Map<Integer, Event>)events);
        }
    }

}
