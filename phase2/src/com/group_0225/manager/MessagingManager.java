package com.group_0225.manager;

import com.group_0225.Main;
import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.EventMessage;
import com.group_0225.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MessagingManager {

    private EventManager ev;

    public MessagingManager(){
       ev = new EventManager();
    }

    /**
     * Sends a request.
     * @param from The user sending the request
     * @param to The user receiving the request
     * @param event The associated event
     * @param message The message (if any)
     */
    public void sendRequest(User from, User to, Event event, String message){
        EventMessage request = new EventMessage(event, message, from, to);
        to.addRequest(request);
    }

    /**
     * Accepts the request.
     * @param request The request
     */
    public void acceptRequest(CalendarData data, EventMessage request){
        EventMessage response = new EventMessage(request.getEvent(), null, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
        // TODO: Important, fix this to accommodate new constructor.
        ev.createEvent(data, request.getEvent().getEventName(), request.getEvent().getTime());
    }

    /**
     * Accepts the request.
     * @param request The request
     */
    public void acceptRequest(CalendarData data, EventMessage request, String message){
        EventMessage response = new EventMessage(request.getEvent(), message, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
        data.getCurrUser().removeRequest(request);
        // TODO: Important, fix this to accommodate new constructor.
        ev.createEvent(data, request.getEvent().getEventName(), request.getEvent().getTime());
    }

    /**
     * Rejects the request.
     * @param request The request
     */
    public void rejectRequest(CalendarData data, EventMessage request){
        EventMessage response = new EventMessage(request.getEvent(), null, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
        data.getCurrUser().removeRequest(request);
    }
    /**
     * Rejects the request, adds a reply.
     * @param request The request
     */
    public void rejectRequest(EventMessage request, String message){
        EventMessage response = new EventMessage(request.getEvent(), message, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
    }

    /**
     * Gets a list of users who are requesting the current user join their event(s)
     * @param data a CalendarData instance
     * @return a list of Strings of usernames
     */
    public List<String> getRequests(CalendarData data){
        List<EventMessage> requests = data.getCurrUser().getRequests();
        List<String> output = new ArrayList<>();
        for (EventMessage request: requests){
            output.add(request.getFrom().getUsername());
        }
        return output;
    }

    /**
     * Return a list of all invitation messages from other users
     * @param data a CalendarData instance
     * @return the list of all invitation messages from other users
     */
    public List<String> getText(CalendarData data){
        List<EventMessage> requests = data.getCurrUser().getRequests();
        List<String> output = new ArrayList<>();
        for (EventMessage request: requests){
            output.add(request.getMessage());
        }
        return output;
    }
    private String formatForMessaging(EventMessage message){

        return "Event Name: " + message.getEvent().getEventName()
                + "\nStart: " + message.getEvent().getStartDateString() + " " + message.getEvent().getStartTimeString()
                + "\nEnd: " + message.getEvent().getEndDateString() + " " + message.getEvent().getEndTimeString();
    }

    /**
     * Formats an Event as a String, in the way required for messages
     * @param u the logged-in user
     * @param message the message
     * @return the formatted event string
     */
    public String messageToEvent(User u, String message){
        return formatForMessaging(u.getMapRequests().get(message));
    }

    /**
     * Return the EventMessage instance corresponding to the message sent
     * @param data CalendarData instance
     * @param message the message sent
     * @return EventMessage instance that corresponds
     */
    public EventMessage getEventMessage(CalendarData data, String message){
        return data.getCurrUser().getMapRequests().get(message);
    }

}
