package com.group_0225.manager;

import com.group_0225.*;
import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.core.CalendarFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
        DatabaseReader databaseReader = new DatabaseReader();
        CalendarData calendarData = new CalendarData();

        populate(calendarData, databaseReader);


        UIPresenter presenter = new UIPresenter();
        ControllerFacade controllerFacade = new ControllerFacade(presenter, calendarData);
        CalendarFrame frame = new CalendarFrame(presenter, calendarData, controllerFacade);
        presenter.addObserver(frame);
        controllerFacade.run();

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
