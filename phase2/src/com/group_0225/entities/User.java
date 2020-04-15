package com.group_0225.entities;

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
public class User implements Serializable {
    //map of calendarNames to list of event ids.
    private Map<String, List<Integer>> calendars = new HashMap<>();
    private Map<Integer, String> memos = new HashMap<>();
    private String password;
    private String username;


    private Map<String,EventMessage> requests;
    private Map<String,EventMessage> responses;


    /**
     * Created an instance of User
     * @param username the username of this user
     * @param password the password of this user
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        requests = new HashMap<>();
        responses = new HashMap<>();
        calendars.put("default", new ArrayList<>());
    }

    /**
     * A getter method for events of a particular calendar of this user.
     * @param calendarName the string representing the name of the calendar to be returned.
     * @return the events of this user.
     */
    public List<Integer> getEvents(String calendarName){return this.calendars.get(calendarName);}

    /**
     * A method to return the names of all the calendars of the user
     * @return the list of calendars of the user.
     */
    public List<String> getCalendars(){
        List<String> retList = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry: calendars.entrySet()){
            retList.add(entry.getKey());
        }
        return retList;
    }
    public Map<String,List<Integer>> getMap(){return this.calendars;}

    public List<Integer> getRandomCalendarEvents(){
        List<String> temp = getCalendars();
        if (temp.size() != 0) {
            return getEvents(getCalendars().get(0));
        }
        return null;
    }


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
        requests.put(request.getMessage(),request);
    }

    public boolean hasRequests(){
        return requests.size() > 0;
    }

    public List<EventMessage> getRequests(){
        return new ArrayList<>(requests.values());
    }
    public EventMessage getRequest(String message){
        return requests.get(message);
    }
    public Map<String, EventMessage> getMapRequests(){
        return requests;
    }
    public Map<String, EventMessage> getMapResponses(){
        return responses;
    }
    public void addResponse(EventMessage response){
        responses.put(response.getMessage(),response);
    }

    public int hashCode(){
        return username.hashCode();
    }

    public void addCalendar(String calendarName){
        List<Integer> ids = new ArrayList<>();
        calendars.put(calendarName, ids);
    }

    public void addIdToCalendars(String calendarName, int id){
        calendars.get(calendarName).add(id);
    }

}
