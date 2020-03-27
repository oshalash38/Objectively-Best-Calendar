package com.group_0225;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a user of the calendar
 *
 * @author Abhijoy Mandal.
 */
public class User implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();
    private Map<Integer, String> memos = new HashMap<>();
    private String password;
    private String username;
    private List<EventMessage> requests;
    private List<EventMessage> responses;


    /**
     * Created an instance of User
     * @param username the username of this user
     * @param password the password of this user
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        requests = new ArrayList<EventMessage>();
    }

    /**
     * A getter method for getEvents
     * @return the events of this user.
     */
    public ArrayList<Event> getEvents(){return this.events;}

    /**
     * A function to make sure if the client entered the correct password to gave the right to interact with the user
     * @param attempt the trial by the client
     * @return true if the client entered the right password. false otherwise.
     */
    public Boolean validatePassword(String attempt){return this.password.equals(attempt);}

    /**
     * A getter method for the username
     * @return the username of this user
     */
    public String getUsername(){return username;}

    /**Return the HashMap containing the memos.
     *
     * @return this.memos
     */
    public Map<Integer, String> getMemos(){return this.memos;}

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

    public void addRequest(EventMessage request){
        requests.add(request);
    }

    public boolean hasRequests(){
        return requests.size() > 0;
    }

    public List<EventMessage> getRequests(){
        return requests;
    }

    public void addResponse(EventMessage response){
        responses.add(response);
    }

}

