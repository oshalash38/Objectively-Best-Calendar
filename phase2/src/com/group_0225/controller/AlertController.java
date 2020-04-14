package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.Arrays;

public class AlertController extends CalendarController {
    public AlertController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void pushCreateNewAlert(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(""), "createAlertPanel"));
    }



    public void pushCreateRepeatingAlert(){

    }

    public void pushCreateOneTimeAlert(){

    }
}
