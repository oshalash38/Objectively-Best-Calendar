package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.Timing;
import com.group_0225.entities.TimingFactory;
import com.group_0225.manager.AlertManager;
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
        pushCreateRepeatingAlertHelper("", "");
    }

    public void pushCreateRepeatingAlert(String alertName, String error){
        pushCreateRepeatingAlertHelper(alertName, error);
    }

    public void pushCreateRepeatingAlertHelper(String alertName, String error){
        EventManager eventManager = new EventManager();

        List<String> outputs = new ArrayList<String>() {{
            addAll(Arrays.asList(error, alertName));
            addAll(eventManager.getNames(data.getCurrUserEvents()));
        }};

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "CreateRepeatingAlertPanel"));

    }

    public void pushCreateOneTimeAlert(){
        pushCreateOneTimeAlertHelper("", "");
    }

    public void pushCreateOneTimeAlert(String alertName, String error){
        pushCreateOneTimeAlertHelper(alertName, error);
    }

    private void pushCreateOneTimeAlertHelper(String alertName, String error){
        EventManager eventManager = new EventManager();

        List<String> outputs = new ArrayList<String>() {{
            addAll(Arrays.asList(error, alertName));
            addAll(eventManager.getNames(data.getCurrUserEvents()));
        }};

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "CreateOneTimeAlertPanel"));

    }


    public void createOneTimeAlert(List<String> inputs){
        TimingFactory timingFactory = new TimingFactory();
        EventManager eventManager = new EventManager();
        AlertManager alertManager = new AlertManager(data.getCurrUserEvents());

        String message = inputs.get(0);
        String eventString = inputs.get(1);

        if(inputs.get(2).equals("")){
            pushCreateOneTimeAlert(message, "A date MUST selected.");
            return;
        }

        List<Integer> date = parseDate(inputs.get(2));
        List<Integer> time = parseTime(inputs.get(3));
        message = message.trim();

        if(eventString == null){
            pushCreateOneTimeAlert("", "Please select an event.");
            return;
        }
        Event event= data.getEventByName(eventString);

        Timing timing = timingFactory.createTiming(date.get(2), date.get(1), date.get(0), time.get(0), time.get(1));

        if(timing.compareStartTime(event.getTime()) > 0){
            pushCreateOneTimeAlert(message, "The alert must occur before the event.");
            return;
        }

        //TODO FIGURE OUt WHY ALERT HAVE IDs AD UNCOMMENT
        //alertManager.createNewAlert(event,)



    }

    public void createRepeatingAlert(List<String> inputs){
        TimingFactory timingFactory = new TimingFactory();
        EventManager eventManager = new EventManager();
        AlertManager alertManager = new AlertManager(data.getCurrUserEvents());

        String message = inputs.get(0);
        String eventString = inputs.get(1);

        if(inputs.get(2).equals("")){
            pushCreateOneTimeAlert(message, "A date MUST selected.");
            return;
        }

        List<Integer> date = parseDate(inputs.get(2));
        List<Integer> time = parseTime(inputs.get(3));
        message = message.trim();

        if(eventString == null){
            pushCreateOneTimeAlert("", "Please select an event.");
            return;
        }
        Event event= data.getEventByName(eventString);

        Timing timing = timingFactory.createTiming(date.get(2), date.get(1), date.get(0), time.get(0), time.get(1));

        if(timing.compareStartTime(event.getTime()) > 0){
            pushCreateOneTimeAlert(message, "The alert must occur before the event.");
            return;
        }

        //TODO FIGURE OUt WHY ALERT HAVE IDs AD UNCOMMENT
        //alertManager.createNewAlert(event,)


    }
}
