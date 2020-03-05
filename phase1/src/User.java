import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a user of the calendar
 *
 * @author Abhijoy Mandal.
 */
public class User implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<Integer, String> memos = new HashMap<>();
    private String password;
    private String username;



    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public ArrayList<Event> getEvents(){return this.events;}

    public Boolean validatePassword(String attempt){return this.password.equals(attempt);}

    public void setPassword (String newPassword){password = newPassword;}

    public void setUsername (String username){ this.username = username;}
    public String getUsername(){return username;}

    /**Return the HashMap containing the memos.
     *
     * @return this.memos
     */
    public HashMap<Integer, String> getMemos(){return this.memos;}

    /**
     *
     * @param l the list of integers for the memos
     * @return List of memo strings
     */
    public List<String> getMemos(List<Integer> l){
        List<String> lst = new ArrayList<String>();
        for(int i: l){
            lst.add(memos.get(i));
        }
        return lst;
    }

    }

