package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.SeriesManager;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Receives information from the GUI about user's choices regarding series, does some parsing, and gives commands to managers
 */
public class SeriesController extends CalendarController {
    /**
     * Constructor that sets up references to required info and presenter rules
     * Yea  * @param data a CalendarData instance
     *
     * @param p a UIPresenter instance
     */
    private SeriesManager sm;
    private EventManager em;
    private UserManager um;

    /**
     * constructor for SeriesController
     *
     * @param data a CalendarData instance
     * @param p    a UIPresenter instance
     */
    public SeriesController(CalendarData data, UIPresenter p) {
        super(data, p);
        sm = new SeriesManager();
        em = new EventManager();
        um = new UserManager();
    }

    /**
     * Called by actionListener in CreateSeriesScratchPanel
     *
     * @param input list of strings including series name, frequency, duration, start date, number of events
     */
    public void createSeriesFromScratch(List<String> input, CalendarGridController calendarGridController) {

        //0 series name must be non-empty string
        //1 string int from 1-5 determining frequency
        //2 start date (day-3ltrmonth-YYYY) (ex. 8-Apr-2020)
        //3 start time (HH:MM:SS)
        //4 string int from 0-6 of duration days
        //5 duration time (HH:MM:SS)
        //6 num events string int
        if (input.get(0).equals("") || input.get(2).equals("")) {
            pushCreateSeriesFromScratchScreen(Collections.singletonList("All fields must be completed."));
        } else if (!verifyDurationLTFreq(input.get(4), input.get(5), input.get(1))) {
            pushCreateSeriesFromScratchScreen(Collections.singletonList("The duration you chose was longer than your frequency."));
        } else {
            String[] split = input.get(2).split("-");
            sm.createSeries(data, input.get(0), input.get(1), input.get(2), Months.get(split[1]), input.get(3), input.get(4), input.get(5), input.get(6), em);
            pushCreateSeriesFromScratchScreen(Collections.singletonList("Series created! You may return to the main menu."));
            calendarGridController.displayGrid();
        }

    }

    /**
     * Called directly from CreateSeriesFromEventsPanel
     *
     * @param sName the name of the new series
     * @param input a list of strings of ids that the user selected and the series name
     */
    public void createSeriesFromEvents(String sName, List<String> input) {
        List<String> args = em.formatAllEventsForSeries(data, um);
        if (sName.equals("")) {
            args.add(0, "All fields must be completed.");
            pushCreateSeriesFromEventsScreen(args);
        } else if (input.size() < 2) {
            args.add(0, "Series must have at least two events.");
            pushCreateSeriesFromEventsScreen(args);
        } else {
            sm.createSeries(data, sName, input);
            args.add(0,"Series created! You may return to the main menu.");
            pushCreateSeriesFromEventsScreen(args);
        }
    }

    /**
     * Present the screen that allows the user to create a series from existing events
     */
    public void createSeriesFromEventsScreen() {
        List<String> inputs = em.formatAllEventsForSeries(data, um);
        inputs.add(0, "");
        pushCreateSeriesFromEventsScreen(inputs);
    }



    /**
     * Presents a dialog that allows the user to create a series from scratch
     */
    public void createSeriesFromScratchScreen() {
        pushCreateSeriesFromScratchScreen(Collections.singletonList(""));
    }

    /**
     * Presents a dialog that gives the user the choice of creating a series from scratch or from existing events
     */
    public void createSeriesChoiceScreen() {
        presenter.updateUI(new UIUpdateInfo("dialog", new ArrayList<>(), "SeriesChoicePanel"));
    }

    /**
     * Pushes the ViewSNameChoicePanel panel to the user.
     */
    public void viewSNameChoiceScreen(){
        List<String> sNames = sm.getAllSeriesNames(data,em);
        if (sNames.size() == 0 || (sNames.size() == 1 && sNames.get(0).equals(""))){
            presenter.updateUI(new UIUpdateInfo("dialog",new ArrayList<>(),"NoSeriesPanel"));
        }
        else{presenter.updateUI(new UIUpdateInfo("dialog",sNames,"ViewSNameChoicePanel"));}
    }

    private boolean verifyDurationLTFreq(String durationDays, String durationHMS, String freq) {
        int days = Integer.parseInt(durationDays);
        int hours = Integer.parseInt(durationHMS.substring(0, 2));
        int minutes = Integer.parseInt(durationHMS.substring(3, 5));
        int seconds = Integer.parseInt(durationHMS.substring(6, 8));
        switch (Integer.parseInt(freq)) {
            case 1: //hourly
                return hours == 0 && days == 0;
            case 2: //daily
                return days == 0;
            default:
                return true;
        }
    }
    private void pushCreateSeriesFromEventsScreen(List<String> args) {
        presenter.updateUI(new UIUpdateInfo("scrollable", args, "CreateSeriesFromEventsPanel"));
    }

    private void pushCreateSeriesFromScratchScreen(List<String> args) {
        presenter.updateUI(new UIUpdateInfo("dialog", args, "CreateSeriesScratchPanel"));
    }

    /**
     * Presents a scrollable dialog that lets the user view the events in the series they have selected
     * @param selectedName a one-element list containing the chosen series name
     */
    public void pushViewSNameScreen(List<String> selectedName) {
        List<Event> seriesEvents = em.getEventsBySeriesName(selectedName.get(0), em.getUserCalendarEvents(data.getEvents(),data.getCurrUser(),data.getCurrCalendar()));
        List<String> output = em.getEventIDs(seriesEvents);
//        List<String> formattedEvents = em.formatEventsForSeries(seriesEvents);
//        formattedEvents.add(0,selectedName.get(0));
        presenter.updateUI(new UIUpdateInfo("scrollable", output,"EventListPanel"));
    }
}
