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
    private String password;
    private String username;
    private List<Integer> memoIDs = new ArrayList<>();


    private Map<String,EventMessage> requests = new HashMap<>();
    private Map<String,EventMessage> responses = new HashMap<>();


    /**
     * Created an instance of User
     * @param username the username of this user
     * @param password the password of this user
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
//        requests = new HashMap<>();
//        responses = new HashMap<>();
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

    public List<Integer> getAllEvents(){
        List<Integer> retList = new ArrayList<>();
        for(Map.Entry<String, List<Integer>> e: this.calendars.entrySet()){
            retList.addAll(e.getValue());
        }
        return retList;
    }

    /**
     * Getter for calendar map
     * @return the calendar map
     */
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
    public List<Integer> getMemos(){return this.memoIDs;}


    /**
     * Adds a request to the user.
     * @param request the request object
     */
    public void addRequest(EventMessage request){
        requests.put(request.getMessage(),request);
    }

    public boolean hasRequests(){
        return requests.size() > 0;
    }

    /**
     * A getter for requests
     * @return list of requests
     */
    public List<EventMessage> getRequests(){
        return new ArrayList<>(requests.values());
    }
    public EventMessage getRequest(String message){
        return requests.get(message);
    }

    /**
     * A getter for requests
     * @return map of requests
     */
    public Map<String, EventMessage> getMapRequests(){
        return requests;
    }
    public Map<String, EventMessage> getMapResponses(){
        return responses;
    }

    /**
     * Adds a response to user.
     * @param response the response object
     */
    public void addResponse(EventMessage response){
        responses.put(response.getMessage(),response);
    }

    public int hashCode(){
        return username.hashCode();
    }

    /**
     * Adds a calendar to list of calendars for the user.
     * @param calendarName the name of the calendar.
     */
    public void addCalendar(String calendarName){
        List<Integer> ids = new ArrayList<>();
        calendars.put(calendarName, ids);
    }

    /**
     * Adds an id to a specified calendar.
     * @param calendarName the name of the calendar specified
     * @param id the id to be added
     */
    public void addIdToCalendars(String calendarName, int id){
        calendars.get(calendarName).add(id);
    }


    /**
     * Removes a request from the user.
     * @param request the request object
     */
    public void removeRequest(EventMessage request) {
        requests.remove(request.getMessage());
    }

    /**
     * Adds a memo id to the memoIDs list.
     * @param id the id to be added
     */
    public void addMemo(Integer id){memoIDs.add(id);}
}

