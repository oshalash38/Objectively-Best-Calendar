package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.User;
import com.group_0225.manager.UserManager;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

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
}
