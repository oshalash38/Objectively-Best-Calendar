package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Performs high-level logic with respect to messages, delegates to managers and receives arguments from GUI
 */
public class UsersCalendarController extends CalendarController {

    /**
     * Creates an instance of UsersCalendarController
     * @param data the CalendarData is where everything is stored
     * @param presenter the presenter that is uses to push panels to the UI
     */
    public UsersCalendarController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    /**
     * Updates the calendars currently displayed in the toolbar
     */
    public void updateCalendars(){
        UserManager userManager = new UserManager();
        User user = data.getCurrUser();
        List<String> outputs = new ArrayList<>();
        outputs.add(data.getCurrCalendar());
        outputs.addAll(userManager.getCalendarNames(user));
        presenter.updateUI(new UIUpdateInfo("toolbar", outputs));
    }

    /**
     * Pushes the add new calendar view to the UI
     */
    public void pushAddNewCalendar(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList("", ""), "CreateCalendarPanel"));
    }

    /**
     * Does input verification. If the input is adequate a new calendar is created and the UI is updated
     * @param calendarChoice the name of the calendar the user wishes to add
     */
    public void addCalendar(String calendarChoice){
        UserManager userManager = new UserManager();
        User user = data.getCurrUser();

        calendarChoice = calendarChoice.trim();

        if(calendarChoice.length() == 0){
            presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(calendarChoice,
                    "Please Enter all fields"), "CreateCalendarPanel"));
        }
        else if(userManager.addCalendar(user, calendarChoice)){
            presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(calendarChoice,
                    "Calendar creation successful"), "CreateCalendarPanel"));
            updateCalendars();
        }
        else
            presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(calendarChoice,
                    "This calendar already exists"), "CreateCalendarPanel"));
    }

    /**
     * Changes the calendar of the user
     * @param newCalendar the name of the calendar that the user would like to change to
     * @param calendarGridController required to update the calendarGrid
     */
    public void changeCalendar(String newCalendar, CalendarGridController calendarGridController){
        data.setCurrCalendar(newCalendar);
        updateCalendars();
        calendarGridController.displayGrid();
    }

}
