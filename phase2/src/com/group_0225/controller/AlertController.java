package com.group_0225.controller;

import com.group_0225.entities.*;
import com.group_0225.manager.AlertManager;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class AlertController extends CalendarController implements Observer {
    private static List<List<String>> notifications = new ArrayList<List<String>>();
    private AlertManager alertManager = new AlertManager();
    private List<Alert> currAlerts = new ArrayList<>();
    private Alert currAlert;
    private Event currEvent;

    /**
     * Creates an AlertController instance
     * @param data a CalendarData instance
     * @param presenter a UIPresenter instance
     */
    public AlertController(CalendarData data, UIPresenter presenter) {

        super(data, presenter);
        alertManager.addObserver(this::update);
    }

    /**
     * Displays a dialog that allows the user to enter info to create a new alert
     */
    public void pushCreateNewAlert(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(""), "CreateAlertPromptPanel"));
    }



    /**
     * Displays a dialog that allows the user to create a new repeating alert
     */
    public void pushCreateRepeatingAlert(){
        pushCreateRepeatingAlertHelper("", "");
    }

    /**
     * Displays a dialog that informs the user of their error in creating a new alert
     * @param alertName name of the alert
     * @param error information about the input error
     */
    public void pushCreateRepeatingAlert(String alertName, String error){
        pushCreateRepeatingAlertHelper(alertName, error);
    }

    /**
     * Displays a dialog that allows the user to create a one-time alert
     */
    public void pushCreateOneTimeAlert(){
        pushCreateOneTimeAlertHelper("", "");
    }

    /**
     * Displays a dialog that informs the user of the error in creating a one-time alert
     * @param alertName name of the alert
     * @param error info about the error
     */
    public void pushCreateOneTimeAlert(String alertName, String error){
        pushCreateOneTimeAlertHelper(alertName, error);
    }


    /**
     * Parses user input and delegates to AlertManager one-time alert creation
     * @param inputs user input
     * @param calendarGridController a CalendarGridController instance
     */
    public void createOneTimeAlert(List<String> inputs, CalendarGridController calendarGridController){
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

    /**
     * Parses user input and delegates to managers the task of creating a repeating alert, recognizes errors
     * @param inputs user input
     * @param calendarGridController a CalendarGridController
     */
    public void createRepeatingAlert(List<String> inputs, CalendarGridController calendarGridController){
        AlertManager alertManager = new AlertManager();
        DurationFactory durationFactory = new DurationFactory();
        String message = inputs.get(0);
        String eventString = inputs.get(1);
        message = message.trim();

        if(eventString == null){
            pushCreateRepeatingAlert(message, "Please select an event.");
            return;
        }
        Event event= data.getEventByName(eventString);

        String freqFeedback = freqVerification(inputs.get(4), inputs.get(5), inputs.get(6));

        if(freqFeedback != null){
            pushCreateRepeatingAlert(message, freqFeedback);
            return;
        }
        Duration freq = durationFactory.createDuration(Integer.parseInt(inputs.get(4)),
                Integer.parseInt(inputs.get(5)), Integer.parseInt(inputs.get(6)));
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

    /**
     * Displays a dialog that allows the user to view all the alerts for the event given
     * @param rawEvent the event formatted as a String
     */
    public void pushViewAlertsPanel(String rawEvent){
        EventManager eventManager = new EventManager();
        int id = Integer.parseInt(rawEvent);

        Event event = eventManager.getEventByID(data, id);
        pushViewAlertsByEvent(event, "");
    }

    /**
     * Displays a dialog that allows the user to edit an alert
     * @param index the index of the relevant alert
     * @param eventID the id of the event that the user is inspecting
     */
    public void pushEditAlert(int index, int eventID){
        AlertManager alertManager = new AlertManager();
        currAlert = currAlerts.get(index);
        List<String> outputs = alertManager.getParameters(currAlert);

        EventManager eventManager = new EventManager();
        currEvent = eventManager.getEventByID(data, eventID);

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "EditAlertButtonsPanel"));

    }

    /**
     * Deletes an alert
     * @param index the index corresponding the alert to be deleted
     * @param eventID the id of the event corresponding to the alert to be deleted
     * @param calendarGridController a CalendarGridController instance
     */
    public void deleteAlert(int index, int eventID, CalendarGridController calendarGridController){

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
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(error), "AlertChangeFrequencyPanel"));
    }

    /**
     * Allows the user to change the time of their alert
     * @param inputs relevant input
     * @param calendarGridController a CalendarGridController instance
     */
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

    /**
     * Changes the message of an alert
     * @param inputs relevant input as Strings
     */
    public void editMessage(List<String> inputs){
        AlertManager alertManager = new AlertManager();
        String message = inputs.get(0);

        message = message.trim();

        alertManager.setMessage(currAlert, message);
        pushChangeMessage("Alert message successfully changed");
    }

    /**
     * Changes the frequency of a repeating alert
     * @param inputs relevant input as Strings
     */
    public void editFrequency(List<String> inputs){
        DurationFactory durationFactory= new DurationFactory();
        AlertManager alertManager = new AlertManager();

        String rawDay = inputs.get(0);
        String rawHour = inputs.get(1);
        String rawMinute = inputs.get(2);
        String freqFeedback = freqVerification(rawDay, rawHour, rawMinute);
        if(freqFeedback!= null){
            pushChangeFrequency(freqFeedback);
            return;
        }
        Duration freq = durationFactory.createDuration(Integer.parseInt(rawDay),
                Integer.parseInt(rawHour), Integer.parseInt(rawMinute));

        RecurringAlert temp = (RecurringAlert)currAlert;
        alertManager.setFreq(temp, freq);

        pushChangeFrequency("Frequency changed successfully.");
    }

    private void pushCreateOneTimeAlertHelper(String alertName, String error){
        EventManager eventManager = new EventManager();
        List<String> outputs = new ArrayList<String>() {{
            addAll(Arrays.asList(error, alertName));
            addAll(eventManager.getNames(data.getCurrUserEvents()));
        }};

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "CreateOneTimeAlertPanel"));
    }

    private void pushCreateRepeatingAlertHelper(String alertName, String error){
        EventManager eventManager = new EventManager();

        List<String> outputs = new ArrayList<String>() {{
            addAll(Arrays.asList(error, alertName));
            addAll(eventManager.getNames(data.getCurrUserEvents()));
        }};

        presenter.updateUI(new UIUpdateInfo("dialog", outputs, "CreateRepeatingAlertPanel"));

    }

    private String freqVerification(String input1, String input2, String input3){
        int freq1;
        int freq2;
        int freq3;
        try{
            freq1 = Integer.parseInt(input1);
            freq2 = Integer.parseInt(input2);
            freq3 = Integer.parseInt(input3);
        }catch (NumberFormatException e){
            return "Frequencies MUST be integers.";
        }
        if((freq1 == 0 && freq2 == 0 && freq3 == 0 ) ||  (freq1 < 0 || freq2 < 0 || freq3 < 0)){
            return "Frequencies MUST be positive.";
        }
        return null;
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




    @Override
    public void update(Observable o, Object arg) {

        List<List<String>> temp = (List<List<String>>)arg;
        if (!temp.isEmpty()){
            notifications.addAll((List<List<String>>)arg);
            List<String> retNotifications = new ArrayList<>();
            for (List<String> s: notifications){
                retNotifications.addAll(s);
            }
            presenter.updateUI(new UIUpdateInfo("dialog", retNotifications, "NotificationListPanel" ));
        }
    }

    /**
     * Dislays the detils of the notification
     * @param inputs the information about the alert to be diplayed
     */
    public void displayNotifications(List<String> inputs){
        presenter.updateUI(new UIUpdateInfo("dialog", inputs, "NotificationPanel" ));
    }

    /**
     * Displays all the notifications
     */
    public void displayAllNotifications(){
        List<String> retNotifications = new ArrayList<>();
        for (List<String> s: notifications){
            retNotifications.addAll(s);
        }

        presenter.updateUI(new UIUpdateInfo("dialog", retNotifications, "NotificationListPanel" ));


    }

    /**
     * starts the timer task in alertManager when user logs in
     */
    public void start(){
        EventManager eventManager = new EventManager();
        List<Integer> eventID = data.getCurrUser().getAllEvents();
        List<Event> events = new ArrayList<>();
        for (int i: eventID){
            events.add(eventManager.getEventByID(data, i));
        }
        alertManager.keepChecking(data, data.getCurrUser());
    }

    /**
     * stops the timer after user logs out
     */
    public void stop(){
        alertManager.stopTimer();
    }

    /**
     * removes the notification after user has viewed it
     * @param i index of notification to be removed
     */
    public void removeNotification(int i){
        notifications.remove(i);
    }

    /**
     * Used to clear the notifications after user logs out
     */
    public void clear(){
        notifications.clear();
    }
}
