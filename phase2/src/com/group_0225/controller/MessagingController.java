package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.entities.Timing;
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
public class MessagingController extends CalendarController{

    private EventManager eventManager;
    private MessagingManager messagingManager;

    /**
     * Makes a MessagingController instance
     * @param data a CalendarData instance
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
    public void pushSendNewMessagePanel(){
        sendNewMessagePanel(Collections.singletonList(""));

    }

    /**
     * Display a panel that allows the user to send a message
     * @param input
     */
    private void sendNewMessagePanel(List<String> input){
        List<String> events = new ArrayList<>(input);
        events.add(eventManager.getEventIDs(data.getCurrUserEvents()).size() + "");
        events.addAll(eventManager.getNames(data.getCurrUserEvents()));
        events.addAll(eventManager.getEventIDs(data.getCurrUserEvents()));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "SendMessage"));
    }

    public void pushInboxPanel(){
        List<String> requests = messagingManager.getRequests(data);
        requests.addAll(messagingManager.getText(data));
        presenter.updateUI(new UIUpdateInfo("dialog", requests, "Inbox"));
    }
    public void pushAcceptDeclinePanel(List<String> message){
        List<String> input = new ArrayList<>(message);
        input.add(messagingManager.messageToEvent(data.getCurrUser(),message.get(0)));
        //index 0 -> the message
        //index 1 -> the event formatted as string
        presenter.updateUI(new UIUpdateInfo("scrollable",input,"AcceptDeclineMessagePanel"));
    }

    public void sendMessage(List<String> input){
        User user = data.getUser(input.get(0));
        Event event = eventManager.getEventByID(data, Integer.parseInt(input.get(1)));
        if (user != null && event != null){
//            User user = data.getUser(input.get(0));
            messagingManager.sendRequest(data.getCurrUser(), user, event, input.get(2));
        }
        else {
            if (user == null){
            sendNewMessagePanel(Collections.singletonList("This user does not exist."));

            }
            else {
                sendNewMessagePanel(Arrays.asList("No events to choose from"));
            }
        }


    }
    public void acceptMessage(List<String> input){
        messagingManager.acceptRequest(data,messagingManager.getEventMessage(data,input.get(0)),input.get(1));
        pushInboxPanel();
    }
    public void declineMessage(List<String> input){
        messagingManager.rejectRequest(messagingManager.getEventMessage(data,input.get(0)),input.get(1));
        pushInboxPanel();
    }

}
