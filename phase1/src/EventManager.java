import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EventManager {
    /**
     * Use case for events.
     * @author Omar Shalash
     */

    /**
     * Creates a new event and returns it.
     * @param user The user that will store the new event.
     * @param name Name of the event.
     * @param timing Timing of the event.
     */
    public void createEvent(User user, String name, Timing timing){
        Event event = new Event(name, timing);
        ArrayList<Event> events = user.getEvents();
        int index = 0;
        while (index < events.size()){
            if (events.get(index).compareTo(event) == 1 || events.get(index).compareTo(event) == 0){
                events.add(2, event);
                break;
            }
        }
        if (index >= events.size()){
            events.add(event);
        }
    }

    /**
     * Deletes an event.
     * @param user The user that contains the event to be removed.
     * @param event The event to be removed.
     * @return True iff the event was removed.
     */
    public boolean deleteEvent(User user, Event event){
        ArrayList<Event> events = user.getEvents();
        return events.remove(event);
    }

    /**
     * Changes the name of an event.
     * @param user The user that contains the event to be altered
     * @param event The event to be altered
     * @param newName The new name.
     */
    public void changeEventName(User user, Event event, String newName){
        Event currEvent = this.searchEventByName(user, event.getEventName());
        if (currEvent != null){
            currEvent.setEventName(newName);
        }
    }



    public Event searchEventByName(User user, String name){
        ArrayList<Event> events = user.getEvents();
        for (Event event: events){
            if (event.getEventName().equals(name)){
                return event;
            }
        }
        return null;
    }
}
