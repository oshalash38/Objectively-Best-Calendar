import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.ArrayList;
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
    public Event createEvent(User user, String name, Timing timing){
        Event event = new Event(name, timing);
        sortEvents(user, event);
        return event;
    }
    public Event createEvent(User user, String name, Timing timing, String series){
        Event event = new Event(name, timing, series);
        sortEvents(user,event);
        return event;
    }
    private void sortEvents(User user, Event e){
        ArrayList<Event> events = user.getEvents();

        for (Event event: events){
            if (event.compareTo(e) >= 0){ events.add(events.indexOf(event), e); return;}
        }
        events.add(e);
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

    /**
     * Updates the status of every event stored in the user.
     * @param user The user.
     */
    public void updateStatus(User user){
        ArrayList<Event> events = user.getEvents();
        for (Event event : events){
            event.updateStatus();
        }
    }

    public ArrayList<Event> getUpcomingEvents(User user){
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.UPCOMING){
                upcomingEvents.add(event);
            }
        }
        return upcomingEvents;
    }

    public ArrayList<Event> getCurrentEvents(User user){
        ArrayList<Event> currentEvents = new ArrayList<>();
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.CURRENT){
                currentEvents.add(event);
            }
        }
        return currentEvents;
    }

    public ArrayList<Event> getPastEvents(User user){
        ArrayList<Event> pastEvents = new ArrayList<>();
        ArrayList<Event> allEvents = user.getEvents();
        for (Event event: allEvents){
            if (event.getStatus() == Status.PAST){
                pastEvents.add(event);
            }
        }
        return pastEvents;
    }

    public void ChangeTime(Event e, List<Integer> input, int type){
        TimingFactory timingFactory = new TimingFactory();
        if(type == 1){
            Timing current = e.getTime();
            e.setTime(timingFactory.createTiming());
        }
        else{

        }

    }

    /**
     *
     * @param events the events that need to be formatted
     * @return a list of the names of the strings provided
     */
    public List<String> formatEventByName(List<Event> events ){
        List<String> nameList = new ArrayList<String>();
        for(Event event: events){
            nameList.add(event.getEventName());
        }
        return nameList;
    }
}
