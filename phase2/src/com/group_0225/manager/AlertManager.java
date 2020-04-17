package com.group_0225.manager;

import com.group_0225.entities.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;



//Code for timer got from https://stackoverflow.com/questions/18353689/how-to-repeat-a-task-after-a-fixed-amount-of-time-in-android

/**
 * @author ABHIJOY MANDAL .
 */
public class AlertManager extends Observable{
    private static LocalDateTime currentTime = LocalDateTime.now();
    private List<Alert> upcomingAlerts = new ArrayList<>(0);
    private List<List<String>> printableUpcomingAlerts = new ArrayList<>(0);
    private User user;
    private CalendarData data;
    private Timer t = new Timer();
    private final int CHECKDURATION = 5;
    private List<Event> UserEvents;
    private TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            AlertManager.this.run();
        }
    };

    private void run(){
        try {

            updateUserEvents(this.data, user);
                List<List<String>> displayAlerts = runUpcomingAlerts();
                if(displayAlerts.size() > 0){
                    setChanged();
                    notifyObservers(displayAlerts);
                }



        } catch (ConcurrentModificationException ex) {
            run();
        }
    }

//    /**
//     *
//     * @param eventIDs initialises alertManager with the events of the logged in user
//     */
//    public AlertManager (CalendarData calendarData, List<Integer> eventIDs){
//        this.UserEvents.clear();
//        for(Map.Entry<Integer, Event> entry: calendarData.getEvents().entrySet()){
//            if(eventIDs.contains(entry.getKey())){
//                UserEvents.add(entry.getValue());
//            }
//        }
//    }

    /**
     * Checks for any alerts which have passed while the program was not running.
     *
     *
     * @return Returns an ArrayList of Alert which have passed.
     */
    public List<List<String>> checkNewAlerts(){
        List<List<String>> retList =  new ArrayList<>(0);
            for (Event e : UserEvents) {
                checkPassedAlertsEvent(e, retList, currentTime);

        }
        if(upcomingAlerts!= null) {
            retList.addAll(runUpcomingAlerts());
        }
        return retList;
    }

    /**
     * Starts the timer task to excecute every CHECKDURATION*1000 seconds.
     */
    public void keepChecking(CalendarData data, User currUser){
        this.user = currUser;
        this.data = data;
        updateUserEvents(data, currUser);
        getUpcomingAlerts();
        System.out.println(upcomingAlerts.size());
        t.scheduleAtFixedRate(timerTask, CHECKDURATION*1000, CHECKDURATION*1000);

    }

    private void updateUserEvents(CalendarData data, User currUser){
        List<Integer> eventIDs = currUser.getAllEvents();
        List<Event> events = new ArrayList<>();
        for(Integer id: eventIDs){
            if(data.getEvents().containsKey(id)) {
                events.add(data.getEvents().get(id));
            }
        }

        this.UserEvents = events;
    }


    /**
     * Stops the timer task when user logs out
     */
    public void stopTimer(){
        timerTask.cancel();
        t.cancel();
        t.purge();

    }

    private void sortAdd(ArrayList<Alert> alerts, Alert newAlert){
        if (!alerts.isEmpty() && newAlert.compareTo(alerts.get(0)) < 0){alerts.add(0, newAlert); return;}
        for (Alert a: alerts){
            if (newAlert.compareTo(a) >= 0){ alerts.add(alerts.indexOf(a), newAlert); return;}
        }
        alerts.add(newAlert);
    }

    private List<String> formatAlertDisplay(Event e, Alert a){
        List<String> retList = new ArrayList<>();
        retList.add("New Alert For: " + e.getEventName());
        retList.add("Event name: " + e.getEventName());
        retList.add("Event Start time: " + e.getTime().toString());
        retList.add("Alert message: " + a.pushReminder());
        retList.add("Alert Time: "+ a.getNextTime().toString());
        return retList;
    }

    private ArrayList<Alert> checkPassedAlertsEvent(Event e, List<List<String>> retList, LocalDateTime T){
        ArrayList<Alert> alerts = e.getAlerts();
        ArrayList<Alert> retAlerts = new ArrayList<>();
        List<Alert> temp = new ArrayList<>();

        //Iterates over each alert
        for(Alert a: alerts) {
            Timing currentAlertTime = a.getNextTime();
            //Checks if alert is scheduled after the current time
            //if it is, then we break here since the list is sorted by time.
            if (a.getNextTime().getStart().compareTo(T)> 0) {
                break;

            } else {

                sortAdd(retAlerts, a);

                //Updates the 'next time' of the alert and checks if it changes
                a.getStatus(T);

                //if it changes then we know it is a recurring alert.
                if (a.getNextTime().compareStartTime(currentAlertTime) != 0) {
                    //then we add in every recurring alert which is scheduled before today.
                    while (a.getNextTime().getStart().compareTo(T) <= 0 && a.getNextTime().getStart().compareTo(e.getTime().getStart())<=0) {
                        sortAdd(retAlerts, a);

                    }
                    //if the above loop is terminated because the event has passed, then we can delete the alert from the ArrayList.
                    if (a.getNextTime().getStart().compareTo(e.getTime().getStart()) >0){temp.add(a);}
                }

                //Else if the alert is a OneTimeAlert, then we can delete it from the ArrayList directly.
                else{
                    temp.add(a);
                }

            }
        }
        for(Alert a: temp){
            e.getAlerts().remove(a);
        }
        for (Alert a: retAlerts){
            retList.add(this.formatAlertDisplay(e, a));
        }
        return retAlerts;
    }

    /**
     * This method compiles a list of a list of alerts in string format
     * @return the upcoming alerts
     */
    public List<List<String>> checkUpcomingAlerts ()
    {
       List<List<String>> retList = new ArrayList<>();
       for(Event e: UserEvents) {
           if (e.getAlerts().size() > 0) {
               retList.add(formatAlertDisplay(e, e.getAlerts().get(0)));
           }
       }
       retList.addAll(printableUpcomingAlerts);
       return retList;
    }

    /**
     * Creates and adds a OneTimeAlert with a message to the event.
     *
     * @param e the event to which the alert is to be added
     * @param T the timing of the alert
     * @param message the message associated with the alert
     * @return returns 1, when the new alert is successfuly added to the list of alerts of the event.
     */
    public int createNewAlert(Event e, Timing T, String message){
        Alert newAlert = new OneTimeAlert(T, message);
        sortAdd(e.getAlerts(), newAlert);
        return 1;
    }
    /**
     * Creates and adds a new OneTimeAlert with no message to the event
     *
     * @param e the event to which the alert is to be added
     * @param T the timing of the alert
     * @return returns 1, when the new alert is successfuly added to the list of alerts of the event.
     */
    public int createNewAlert(Event e, Timing T){
        Alert newAlert = new OneTimeAlert(T);
        sortAdd(e.getAlerts(), newAlert);
        return 1;
    }

    /**
     * Creates and adds a new RecurringAlert with a message to the event
     *
     * @param e the event to which the alert is to be added
     * @param T the timing of the alert
     * @param message the message associated with the alert.
     * @param freq the duration after which the alert is repeated.
     * @return returns 1, when the new alert is successfuly added to the list of alerts of the event.
     */
    public int createNewAlert(Event e, Timing T, String message, Duration freq){
        Alert newAlert = new RecurringAlert(T, message, freq);
        sortAdd(e.getAlerts(), newAlert);
        return 1;
    }
    /**
     * Creates and adds a new RecurringAlert with no message to the event
     *
     * @param e the event to which the alert is to be added
     * @param T the timing of the alert
     * @return returns 1, when the new alert is successfuly added to the list of alerts of the event.
     */
    public int createNewAlert(Event e, Timing T, Duration freq){
        Alert newAlert = new RecurringAlert(T, freq);
        sortAdd(e.getAlerts(), newAlert);
        return 1;
    }

    private void getUpcomingAlerts(){
        for (Event e: UserEvents){
            LocalDateTime nextUpdateTime = currentTime.plusSeconds(CHECKDURATION);
            upcomingAlerts = checkPassedAlertsEvent(e, printableUpcomingAlerts, nextUpdateTime);
        }
    }

    private List<List<String>> runUpcomingAlerts(){
        List<List<String>> tempPrint= new ArrayList<>();
        tempPrint.addAll(printableUpcomingAlerts);
        upcomingAlerts.clear();
        printableUpcomingAlerts.clear();
        currentTime = currentTime.plusSeconds(CHECKDURATION);
        getUpcomingAlerts();
        return tempPrint;
    }

    /**
     * Displays the list of reminders formatted nicely
     * @param e the event associated with the reminders
     * @return a string as a representation of all the reminders
     */
    public String formatReminders(Event e){
        String toDisplay = "";
        int counter = 1;
        for(Alert alert: e.getAlerts()){
            if(counter != 1){
                toDisplay+="\n";
            }
            toDisplay += " This is alert #" + counter+"\n";
            toDisplay += "  This alert's message is " + alert.pushReminder()+"\n";
            toDisplay += "  This alert will next activate at " + alert.getNextTime();
            counter++;
        }
        return toDisplay;
    }

    public List<String> getStringRepresentation(Alert alert){
        List<String> info = new ArrayList<>();
        info.add("Message: " + alert.pushReminder());
        info.add("Time: " + alert.getNextTime());
        info.add("Type: " + alert.getType());
        return info;
    }

    public void setCurrentTime(LocalDateTime time){
        this.currentTime = time;
        //checkNewAlerts();
    }
    public List<String> getParameters(Alert alert){
        return alert.getParameters();
    }

    public void setTiming(Alert alert, Timing timing){
        alert.setTime(timing);
    }

    public void setMessage(Alert alert, String message){
        alert.setMessage(message);
    }

    public void setFreq(RecurringAlert alert, Duration freq){alert.setFreq(freq);}
}
