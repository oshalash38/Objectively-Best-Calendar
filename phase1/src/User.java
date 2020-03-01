import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the calendar
 *
 * @author Abhijoy Mandal
 */
public class User implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();

    /** adds an event to this.events
     *
     * @param newEvent the name of the event
     */
    public void addEvent (Event newEvent){
        sortEvents(newEvent);
    }

    /** returns ArrayList of events in User
     * @return this.events
     */
    public ArrayList<Event> getEvents(){return this.events;}

    /** Adds a new event in the sorted Arraylist this.events, and maintains the order.
     *
     * @param newEvent the event to be added to the ArrayList
     */
    private void sortEvents (Event newEvent){
        for (Event e : this.events){
            if (e.compareTo(newEvent) >= 0){
                this.events.add(events.indexOf(e),  newEvent);
                return;
            }
        }
        this.events.add(newEvent);
    }

    /** searches an event in this.events by name
     *
     * @param name the name of the event
     * @return the event if it exists, else null.
     */
    public Event searchEventByName(String name){
        for (Event e: this.events){
            if (e.getEventName().equals(name)){return e;}
        }
        return null;
    }

    /** searches for a series by series name
     *
     * @param sname the series name that determines which events are returned
     * @return an arraylist of events in this series, or an empty arraylist if no series with this name
     */
    public ArrayList<Event> searchEventBySeriesName(String sname){
        ArrayList<Event> toReturn = new ArrayList<>();
        for (Event e: this.events){
            if (e.getSeriesName().equals(sname)){
                toReturn.add(e);
            }
        }
        return toReturn;
    }

}
