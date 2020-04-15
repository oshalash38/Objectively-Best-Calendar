package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlertController extends CalendarController {
    public AlertController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void pushCreateNewAlert(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(""), "CreateAlertPromptPanel"));
    }



    public void pushCreateRepeatingAlert(){

    }

    public void pushCreateOneTimeAlert(){
        EventManager eventManager = new EventManager();

         List<String> outputs = new ArrayList<String>() {{
             addAll(Arrays.asList("", ""));
             addAll(eventManager.getNames(data.getCurrUserEvents()));
         }};

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "CreateOneTimeAlertPanel"));
    }


    public void createOneTimeAlert(List<String> inputs){


    }
}
