import java.util.ArrayList;
import java.util.Collections;

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
     * @return The event created.
     */
    public void createEvent(User user, String name, Timing timing){
        Event event = new Event(name, timing);
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
        Event currEvent = user.searchEventByName(event.getEventName());
        if (currEvent != null){
            currEvent.setEventName(newName);
        }
    }
}
