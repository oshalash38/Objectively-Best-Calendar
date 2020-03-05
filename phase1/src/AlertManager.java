import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


//Code for timer got from https://stackoverflow.com/questions/18353689/how-to-repeat-a-task-after-a-fixed-amount-of-time-in-android

public class AlertManager {
    private LocalDateTime currentTime = LocalDateTime.now();
    private ArrayList<Alert> upcomingAlerts = new ArrayList<>();
    Timer t = new Timer();
    private int CHECKDURATION = 60;
    private ArrayList<Event> UserEvents;

    public AlertManager (ArrayList<Event> events){
        this.UserEvents = events;

    }

    /**
     * Checks for any alerts which have passed while the program was not running.
     *
     *
     * @return Returns an ArrayList of Alert which have passed.
     */
    public ArrayList<Alert> checkNewAlerts(){
        ArrayList<Alert> retAlerts = new ArrayList<>();
        for(Event e: UserEvents){
            checkPassedAlertsEvent(e, retAlerts, currentTime);
        }
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                        runUpcomingEvents();
                                  }

                              },
                0,
                CHECKDURATION*1000);
        return retAlerts;
    }

    private void sortAdd(ArrayList<Alert> alerts, Alert newAlert){
        for (Alert a: alerts){
            if (newAlert.compareTo(a) >= 0){ alerts.add(alerts.indexOf(a), newAlert); break;}
        }
        alerts.add(newAlert);
    }

    private void checkPassedAlertsEvent(Event e, ArrayList<Alert> retAlerts, LocalDateTime T){
        ArrayList<Alert> alerts = e.getAlerts();
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
                    if (a.getNextTime().getStart().compareTo(e.getTime().getStart()) >0){alerts.remove(a);}
                }

                //Else if the alert is a OneTimeAlert, then we can delete it from the ArrayList directly.
                else{alerts.remove(a);}

            }
        }
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
            checkPassedAlertsEvent(e, upcomingAlerts, nextUpdateTime);
        }
    }

    private ArrayList<Alert> runUpcomingEvents(){
        ArrayList<Alert> tempAlerts = new ArrayList<>(upcomingAlerts);
        upcomingAlerts.clear();
        currentTime = LocalDateTime.now();
        getUpcomingAlerts();
        return tempAlerts;
    }

    /**
     *
     * @param afterTime the number of seconds till the alerts
     * @return Returns an ArrayList of alerts which will excecute after afterTime seconds.
     */
    public ArrayList<Alert> getUpcomingAlerts(int afterTime){
        ArrayList<Alert> retList = new ArrayList<>();
        LocalDateTime checkTime = currentTime.plusSeconds(afterTime);
        if(afterTime <CHECKDURATION) {
            for (Alert a : upcomingAlerts) {
                if (a.getNextTime().getStart().compareTo(checkTime) <= 0) {
                    retList.add(a);
                }
            }
        }
        else if (afterTime == CHECKDURATION){retList.addAll(upcomingAlerts);}
        else{
            retList.addAll(upcomingAlerts);
            for(Event e: this.UserEvents){
                checkPassedAlertsEvent(e, retList, checkTime);
            }

        }

        return retList;
    }

}
