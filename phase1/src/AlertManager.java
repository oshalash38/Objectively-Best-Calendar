
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;



//Code for timer got from https://stackoverflow.com/questions/18353689/how-to-repeat-a-task-after-a-fixed-amount-of-time-in-android

/**
 * @author ABHIJOY MANDAL .
 */
public class AlertManager extends Observable{
    private LocalDateTime currentTime = LocalDateTime.now();
    private ArrayList<Alert> upcomingAlerts = new ArrayList<>(0);
    private List<List<String>> printableUpcomingAlerts = new ArrayList<>(0);
    private Map<Event, List<List<String>>> printableLink = new HashMap<>(0);
    private Timer t = new Timer();
    private final int CHECKDURATION = 5;
    private ArrayList<Event> UserEvents;
    private TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            AlertManager.this.run();
        }
    };

    private void run(){
        try {
            List<List<String>> displayAlerts = runUpcomingAlerts();
            setChanged();
            notifyObservers(displayAlerts);
        } catch (ConcurrentModificationException ex) {
            run();
        }
    }
    public AlertManager (ArrayList<Event> events){
        this.UserEvents = events;
    }

    /**
     * Checks for any alerts which have passed while the program was not running.
     *
     *
     * @return Returns an ArrayList of Alert which have passed.
     */
    public List<List<String>> checkNewAlerts(){
        currentTime = LocalDateTime.now();
        List<List<String>> retList =  new ArrayList<>(0);
            for (Event e : UserEvents) {
                checkPassedAlertsEvent(e, retList, currentTime);

        }
        if(upcomingAlerts!= null) {
            retList.addAll(runUpcomingAlerts());
        }
        return retList;
    }

    public void keepChecking(){
        t.scheduleAtFixedRate(timerTask, CHECKDURATION*1000, CHECKDURATION*1000);
    }

    public void stopTimer(){
        timerTask.cancel();
        t.cancel();
        t.purge();
        return;
    }

    private void sortAdd(ArrayList<Alert> alerts, Alert newAlert){
        for (Alert a: alerts){
            if (newAlert.compareTo(a) >= 0){ alerts.add(alerts.indexOf(a), newAlert); return;}
        }
        alerts.add(newAlert);
    }

    private List<String> formatAlertDisplay(Event e, Alert a){
        List<String> retList = new ArrayList<>();
        retList.add("New Alert for:");
        retList.add("Event name: " + e.getEventName());
        retList.add("Event Start time: " + e.getTime().toString());
        retList.add("Alert message: " + a.pushReminder());
        retList.add("Alert Time: "+ a.getNextTime().toString());
        return retList;
    }

    private ArrayList<Alert> checkPassedAlertsEvent(Event e, List<List<String>> retList, LocalDateTime T){
        ArrayList<Alert> alerts = e.getAlerts();
        ArrayList<Alert> retAlerts = new ArrayList<>();
        List<Alert> temp = new ArrayList<Alert>();

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
        printableLink.put(e, retList);
        return retAlerts;
    }

    /**
     * This method compiles a list of a list of alerts in string format
     * @return the upcoming alerts
     */
    public List<List<String>> checkUpcomingAlerts ()
    {
       List<List<String>> retList = new ArrayList<>();
       for(Event e: this.UserEvents) {
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
        printableLink.clear();
        currentTime = LocalDateTime.now();
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

}
