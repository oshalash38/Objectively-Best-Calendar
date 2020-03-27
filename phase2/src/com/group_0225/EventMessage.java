package com.group_0225;

public class EventMessage {
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

    public Event getEvent() {
        return event;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

}