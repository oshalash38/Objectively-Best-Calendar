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
    public void run() throws IOException, ClassNotFoundException {
        DatabaseReader databaseReader = new DatabaseReader();
        CalendarData calendarData = new CalendarData();

        populate(calendarData, databaseReader);

        CalendarFrame frame = new CalendarFrame();
        UIPresenter presenter = new UIPresenter();
        ControllerFacade controllerFacade = new ControllerFacade(presenter, calendarData);
        presenter.addObserver(frame);
        frame.run(presenter, calendarData, controllerFacade);

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
            calendarData.setUsers((Map<String, User>)users);
        }
        if(events != null){
            calendarData.setEvents((Map<Integer, Event>)events);
        }
    }

}
