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
    private Alert currAlert;
    private Event currEvent;

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

        message = message.trim();

        if(eventString == null){
            pushCreateOneTimeAlert("", "Please select an event.");
            return;
        }
        Event event= data.getEventByName(eventString);

        String feedback = dateVerification(inputs.get(2), inputs.get(3), event);

        if(feedback != null){
            pushCreateOneTimeAlert(message, feedback);
            return;
        }
        Timing timing = buildTiming(inputs.get(2), inputs.get(3));

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

        String feedback = dateVerification(inputs.get(2), inputs.get(3), event);

        if(feedback != null){
            pushCreateRepeatingAlert(message, feedback);
            return;
        }
        Timing timing = buildTiming(inputs.get(2), inputs.get(3));

        if(message.equals("")){
            alertManager.createNewAlert(event,timing, freq);
        }
        else {
            alertManager.createNewAlert(event, timing, message, freq);
        }
        pushCreateRepeatingAlert(message, "Alert created successfully.");
        calendarGridController.displayGrid();
    }

    private String dateVerification(String date, String time, Event event){
        if(time.equals("")){
            return "A date MUST be selected.";
        }

        Timing timing = buildTiming(date, time);
        if(timing.compareStartTime(event.getTime()) > 0){
            return "The alert must occur before the event.";
        }
        return null;
    }

    private Timing buildTiming(String date, String time){
        TimingFactory timingFactory = new TimingFactory();
        List<Integer> dateList = parseDate(date);
        List<Integer> timeList = parseTime(time);
        Timing timing = timingFactory.createTiming(dateList.get(2), dateList.get(1), dateList.get(0), timeList.get(0), timeList.get(1));

        return timing;
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

    public void pushEditAlert(int index, int eventID){
        AlertManager alertManager = new AlertManager();
        currAlert = currAlerts.get(index);
        List<String> outputs = alertManager.getParameters(currAlert);

        EventManager eventManager = new EventManager();
        currEvent = eventManager.getEventByID(data, eventID);

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "EditAlertButtonsPanel"));

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

    /**
     * Called by JPanel to see what parameter the user wants to change
     * @param chosenParameter "time" or
     *                        "message" or
     *                        "frequency"
     *
     */
    public void editAlert(String chosenParameter){
        switch (chosenParameter){
            case "time":
                pushChangeTime("");
                break;
            case "message":
                pushChangeMessage("");
                break;
            case "frequency":
                pushChangeFrequency("");
                break;

        }
    }

    private void pushChangeTime(String error){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(error), "AlertChangeTimePanel"));
    }

    private void pushChangeMessage(String error){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(error), "AlertChangeMessagePanel"));
    }

    private void pushChangeFrequency(String error)
    {

    }

    public void editTime(List<String> inputs, CalendarGridController calendarGridController){
        String feedback = dateVerification(inputs.get(0), inputs.get(1), currEvent);
        AlertManager alertManager = new AlertManager();

        if(feedback != null){
            pushChangeTime(feedback);
            return;
        }
        Timing timing = buildTiming(inputs.get(0), inputs.get(1));

        alertManager.setTiming(currAlert, timing);
        pushChangeTime("Time changed successfully");
        calendarGridController.displayGrid();
    }

    public void editMessage(List<String> inputs){
        AlertManager alertManager = new AlertManager();
        String message = inputs.get(0);

        message = message.trim();

        alertManager.setMessage(currAlert, message);
        pushChangeMessage("Alert message successfully changed");
    }



}
