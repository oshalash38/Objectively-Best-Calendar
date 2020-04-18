package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.User;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.MessagingManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Performs high-level logic with respect to messages, delegates to managers and receives arguments from GUI
 */
public class MessagingController extends CalendarController {

    private EventManager eventManager;
    private MessagingManager messagingManager;

    /**
     * Makes a MessagingController instance
     *
     * @param data      a CalendarData instance
     * @param presenter a UIPresenter instance
     */
    public MessagingController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        eventManager = new EventManager();
        messagingManager = new MessagingManager();
    }

    /**
     * Display a panel that allows the user to send a new message, with no error message
     */
    public void pushSendNewMessagePanel() {
        sendNewMessagePanel(Collections.singletonList(""));

    }

    /**
     * Display a panel that allows the user to send a message
     *
     * @param input
     */
    private void sendNewMessagePanel(List<String> input) {
        List<String> events = new ArrayList<>(input);
        events.add(eventManager.getEventIDs(data.getCurrUserEvents()).size() + "");
        events.addAll(eventManager.getNames(data.getCurrUserEvents()));
        events.addAll(eventManager.getEventIDs(data.getCurrUserEvents()));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "SendMessage"));
    }

    /**
     * Displays a dialog that allows the user to view the invitations they've received as well as replies to the invitations they've sent
     */
    public void pushInboxPanel() {
        List<String> output = new ArrayList<>();
        output.add("RequestUsers");
        List<String> requests = messagingManager.getRequests(data);
        output.addAll(requests);
        output.add("RequestMessages");
        List<String> requestMessages = messagingManager.getText(data);
        output.addAll(requestMessages);
        List<String> responses = messagingManager.getResponses(data);
        output.add("ResponseUsers");
        output.addAll(responses);
        output.add("ResponseMessages");
        output.addAll(messagingManager.getResponsesText(data));
//        output.add("ResponseExMessages");
//        output.addAll(messagingManager.getPreviousRequests(responses));
        requests.addAll(messagingManager.getText(data));
        presenter.updateUI(new UIUpdateInfo("dialog", output, "Inbox"));
    }

    /**
     * Displays the message that the user has selected along with an option to accept/decline
     *
     * @param message the message that the user has selected
     */
    public void pushAcceptDeclinePanel(List<String> message) {
        List<String> input = new ArrayList<>(message);
        input.add(messagingManager.messageToEvent(data.getCurrUser(), message.get(0)));
        //index 0 -> the message
        //index 1 -> the event formatted as string
        presenter.updateUI(new UIUpdateInfo("scrollable", input, "AcceptDeclineMessagePanel"));
    }

    /**
     * Sends a message to a user unless input is incorrect, in that case, display the send message panel again
     *
     * @param input relevant user input
     */
    public void sendMessage(List<String> input) {
        User user = data.getUser(input.get(0));
        Event event = eventManager.getEventByID(data, Integer.parseInt(input.get(1)));
        if (user != null && event != null) {
            if (user == data.getCurrUser()) {
                sendNewMessagePanel(Collections.singletonList("You can't send messages to yourself."));
            } else {
                messagingManager.sendRequest(data.getCurrUser(), user, event, input.get(2));
                sendNewMessagePanel(Collections.singletonList("Sent!"));
            }
        } else {
            if (user == null) {
                sendNewMessagePanel(Collections.singletonList("This user does not exist."));

            } else {
                sendNewMessagePanel(Arrays.asList("No events to choose from"));
            }
        }


    }

    /**
     * Accepts an invitation from another user
     *
     * @param input relevant input
     */
    public void acceptMessage(List<String> input, CalendarGridController calendarGridController) {
        messagingManager.acceptRequest(data, messagingManager.getEventMessage(data, input.get(0)), input.get(1));
        calendarGridController.displayGrid();
        pushInboxPanel();
    }

    /**
     * Declines an invitation from another user
     *
     * @param input relevant input
     */
    public void declineMessage(List<String> input) {
        messagingManager.rejectRequest(data, messagingManager.getEventMessage(data, input.get(0)), input.get(1));
        pushInboxPanel();
    }

    /**
     * Displays a dialog that allows the user to view responses to the invitations they've sent
     *
     * @param message
     */
    public void pushResponsePanel(List<String> message) {
        //index 0 -> from
        //index 1 -> the message
        presenter.updateUI(new UIUpdateInfo("dialog", message, "ResponsePanel"));
    }

}
