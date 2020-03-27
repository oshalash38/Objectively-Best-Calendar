package com.group_0225;

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
    public void acceptRequest(EventMessage request){
        EventMessage response = new EventMessage(request.getEvent(), null, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
        ev.createEvent(request.getTo(), request.getEvent().getEventName(), request.getEvent().getTime());
    }

    /**
     * Accepts the request.
     * @param request The request
     */
    public void acceptRequest(EventMessage request, String message){
        EventMessage response = new EventMessage(request.getEvent(), message, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
        ev.createEvent(request.getTo(), request.getEvent().getEventName(), request.getEvent().getTime());
    }

    /**
     * Rejects the request.
     * @param request The request
     */
    public void rejectRequest(EventMessage request){
        EventMessage response = new EventMessage(request.getEvent(), null, request.getTo(), request.getFrom());
        request.getFrom().addResponse(response);
    }
}
