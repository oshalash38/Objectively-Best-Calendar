import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();

    public void createEvent (String name, Timing time, String sname){
        sortEvents(new Event(name, time, sname));
    }

    public void createEvent (String name, Timing time){
        sortEvents(new Event(name, time));
    }

    public ArrayList<Event> getEvents(){return this.events;}

    private void sortEvents (Event newEvent){
        for (Event e : this.events){
            if (e.compareTo(newEvent) >= 0){
                this.events.add(events.indexOf(e),  newEvent);
                return;
            }
        }
        this.events.add(newEvent);
    }

    public Event searchEventByName(String name){
        for (Event e: this.events){
            if (e.getEventName().equals(name)){return e;}
        }
        return null;
    }

}
