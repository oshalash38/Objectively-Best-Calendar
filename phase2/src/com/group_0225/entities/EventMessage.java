package com.group_0225.entities;

import java.io.Serializable;

/**
 * This class contains an event and an associated message, used for sending invitations to events
 */
public class EventMessage implements Serializable {
    private Event event;
    private String message;
    private User from;
    private User to;

    /**
     * Creates a new message.
     * @param event The event associated with the message.
     * @param message The text message.
     * @param from The user sending the message.
     * @param to The user receiving the message.
     */
    public EventMessage(Event event, String message, User from, User to){
        this.event = event;
        this.message = message;
        this.from = from;
        this.to = to;
    }

    /**
     * Getter for the event
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Getter for the from user
     * @return  the from user
     */
    public User getFrom() {
        return from;
    }

    /**
     * Getter for the to user
     * @return  the to user
     */
    public User getTo() {
        return to;
    }

    /**
     * Getter for the message
     * @return  the message
     */
    public String getMessage() {
        return message;
    }

}
