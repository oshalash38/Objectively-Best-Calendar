package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.Arrays;
import java.util.List;

public class UsersCalendarController extends CalendarController {

    public UsersCalendarController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void updateCalendars(){
        UserManager userManager = new UserManager();
        User user = data.getCurrUser();
        presenter.updateUI(new UIUpdateInfo("toolbar", userManager.getCalendarNames(user)));
    }

    public void pushAddNewCalendar(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList("", ""), "CreateCalendarPanel"));
    }

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

    public void changeCalendar(String newCalendar){
        data.setCurrCalendar(newCalendar);
    }

}
