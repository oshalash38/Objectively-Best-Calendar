package com.group_0225.manager;

import com.group_0225.DatabaseReader;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.TimingFactory;
import com.group_0225.entities.User;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.core.CalendarFrame;
import com.group_0225.ui.core.CalendarToolBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class initializes all the classes needed for the program to run.
 *
 * @author Daniel Shoichet
 */

public class Init {

    //PURELY FOR TESTING
    public static UIPresenter p;

    public void run() throws IOException, ClassNotFoundException {
        TimingFactory timingFactory = new TimingFactory();
        DatabaseReader databaseReader = new DatabaseReader();
        CalendarData calendarData = new CalendarData();
        calendarData.setLocalTime(timingFactory.createTiming(LocalDateTime.now()));

        populate(calendarData, databaseReader);


        // From https://stackoverflow.com/questions/2592207/how-to-improve-look-and-feel-of-java-swing-gui
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        UIPresenter presenter = new UIPresenter();
        ControllerContainer controllerContainer = new ControllerContainer(presenter, calendarData);
        CalendarToolBar calendarToolBar = new CalendarToolBar(controllerContainer);
        CalendarFrame frame = new CalendarFrame(presenter, calendarData, controllerContainer, calendarToolBar);
        presenter.addObserver(calendarToolBar);
        presenter.addObserver(frame);
        controllerContainer.getLoginController().startUp();

        p = presenter;

        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println("saving to file");
                    databaseReader.saveToFile("users.txt", calendarData.getUsers());
                    databaseReader.saveToFile("events.txt", calendarData.getEvents());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void populate(CalendarData calendarData, DatabaseReader databaseReader) throws IOException, ClassNotFoundException {

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
