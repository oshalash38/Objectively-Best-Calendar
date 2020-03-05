import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a user of the calendar
 *
 * @author Abhijoy Mandal
 */
public class User implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<Integer, String> memos = new HashMap<>();
    private String password;

    /**Return Arralist of events.
     *
     * @return this.evens
     */
    public ArrayList<Event> getEvents(){return this.events;}

    public Boolean validatePassword(String attempt){return this.password.equals(attempt);}

    public void setPassword (String newPassword){password = newPassword;}

    /**Return the HashMap containing the memos.
     *
     * @return this.memos
     */
    public HashMap<Integer, String> getMemos(){return this.memos;}

    }

