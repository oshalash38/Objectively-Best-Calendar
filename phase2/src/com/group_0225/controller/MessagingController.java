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
import java.util.List;

public class MessagingController extends CalendarController{

    private EventManager eventManager;
    private MessagingManager messagingManager;

    public MessagingController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        eventManager = new EventManager();
        messagingManager = new MessagingManager();
    }

    public void pushSendNewMessagePanel(){

        //TODO the following line was previously eventManager.getEventNames(), but that didn't compile...
        List<String> events = new ArrayList<>();
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
        try {
            Event event = eventManager.getEventByID(data, Integer.parseInt(input.get(1)));
            User user = data.getUser(input.get(0));
            messagingManager.sendRequest(data.getCurrUser(), user, event, input.get(2));
        } catch (NullPointerException e){
            System.err.println("Needs Validation");
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
