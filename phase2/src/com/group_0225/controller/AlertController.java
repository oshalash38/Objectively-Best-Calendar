package com.group_0225.controller;

import com.group_0225.entities.*;
import com.group_0225.manager.AlertManager;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlertController extends CalendarController {
    private List<Alert> currAlerts = new ArrayList<>();

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


    public void createOneTimeAlert(List<String> inputs, CalendarGridController calendarGridController){
        TimingFactory timingFactory = new TimingFactory();
        EventManager eventManager = new EventManager();
        AlertManager alertManager = new AlertManager();

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

        if(message.equals("")){
            alertManager.createNewAlert(event,timing);
        }
        else {
            alertManager.createNewAlert(event, timing, message);
        }
        pushCreateOneTimeAlert(message, "Alert created successfully.");
        calendarGridController.displayGrid();
    }

    public void createRepeatingAlert(List<String> inputs, CalendarGridController calendarGridController){
        TimingFactory timingFactory = new TimingFactory();
        EventManager eventManager = new EventManager();
        AlertManager alertManager = new AlertManager();
        DurationFactory durationFactory = new DurationFactory();

        int days;
        int hours;
        int minutes;


        String message = inputs.get(0);
        String eventString = inputs.get(1);

        if(inputs.get(2).equals("")){
            pushCreateRepeatingAlert(message, "A date MUST be selected.");
            return;
        }

        List<Integer> date = parseDate(inputs.get(2));
        List<Integer> time = parseTime(inputs.get(3));
        message = message.trim();

        if(eventString == null){
            pushCreateRepeatingAlert(message, "Please select an event.");
            return;
        }
        Event event= data.getEventByName(eventString);

        try{
            days = Integer.parseInt(inputs.get(4));
            hours = Integer.parseInt(inputs.get(5));
            minutes = Integer.parseInt(inputs.get(6));
        }catch (NumberFormatException e){
            pushCreateRepeatingAlert(message, "Frequencies MUST be integers.");
            return;
        }

        if((days == 0 && hours == 0  && minutes == 0) ||  (days < 0 || hours < 0  || minutes < 0)){
            pushCreateRepeatingAlert(message, "Frequencies MUST be positive.");
            return;
        }

        Duration freq = durationFactory.createDuration(days, hours, minutes);
        Timing timing = timingFactory.createTiming(date.get(2), date.get(1), date.get(0), time.get(0), time.get(1));

        if(timing.compareStartTime(event.getTime()) > 0){
            pushCreateRepeatingAlert(message, "The alert must occur before the event.");
            return;
        }

        if(message.equals("")){
            alertManager.createNewAlert(event,timing, freq);
            pushCreateRepeatingAlert(message, "Alert created successfully.");
            calendarGridController.displayGrid();
        }
        else {
            alertManager.createNewAlert(event, timing, message, freq);
        }
        pushCreateRepeatingAlert(message, "Alert created successfully.");
        calendarGridController.displayGrid();
    }

    public void pushViewAlertsPanel(String rawEvent){
        EventManager eventManager = new EventManager();
        int id = Integer.parseInt(rawEvent);

        Event event = eventManager.getEventByID(data, id);
        pushViewAlertsByEvent(event, "");
    }

    private void pushViewAlertsByEvent(Event event, String error){
        EventManager eventManager = new EventManager();
        AlertManager alertManager = new AlertManager();

        List<Alert> alerts = eventManager.getAlerts(event);
        currAlerts = alerts;


        if(alerts.size() == 0){
            List<String> temp = Arrays.asList("No alerts for this event.", "");
            presenter.updateUI(new UIUpdateInfo("scrollable", temp, "AlertListPanel"));
            return;
        }
        List<String> toUpload = new ArrayList<>();
        toUpload.add(error);
        toUpload.add(event.getID().toString());
        for(Alert alert: alerts){
            toUpload.addAll(alertManager.getStringRepresentation(alert));
        }
        presenter.updateUI(new UIUpdateInfo("scrollable", toUpload, "AlertListPanel"));
    }

    public void editAlert(int index, int eventID, String info){
        if(info.equals("One time alert")){

        }
    }

    public void deleteAlert(int index, int eventID, CalendarGridController calendarGridController){
        System.out.println(index);

        EventManager eventManager = new EventManager();
        Alert toRemove = currAlerts.remove(index);
        Event event = eventManager.getEventByID(data, eventID);
        eventManager.removeAlert(event, toRemove);

        pushViewAlertsByEvent(event, "Alert deleted successfully.");
        calendarGridController.displayGrid();
    }
}
