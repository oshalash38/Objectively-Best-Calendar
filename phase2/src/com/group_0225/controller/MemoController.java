package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.MemoManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles high-level logic pertaining to memos
 */
public class MemoController extends CalendarController{

    private MemoManager memoManager;
    private EventManager eventManager;

    /**
     * Creates a MemoController instance
     * @param data a CalendarData instance
     * @param presenter a UIPresenter instance
     */
    public MemoController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        memoManager = new MemoManager();
        eventManager = new EventManager();
    }

    /**
     * Creates a memo and adds it to the events
     * @param memo the memo to be created
     * @param eventNames IDs of events that memo should be added to
     */
    public void createMemo(String memo, List<String> eventNames){
        List<String> events = new ArrayList<>();
        if (eventNames.size() == 0){
            events.add("Error1");
        } else if (memo.equals("")){
            events.add("Error2");
        }
        else{
            memoManager.CreateMemo(data, memo, data.getEventsByNames(eventNames));
            events.add("Created");
        }
        events.addAll(eventManager.getNames(data.getCurrUserEvents()));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "CreateMemoPanel"));
    }

    /**
     * pushes the CreateMemoPanel panel
     */
    public void pushCreateMemo(){
        List<String> events = new ArrayList<>();
        events.add(" ");
        events.addAll(eventManager.getNames(data.getCurrUserEvents()));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "CreateMemoPanel"));
    }

    /**
     * pushes the MemoListPanel panel
     */
    public void pushDisplayMemos() {
        List<String> output = new ArrayList<>(memoManager.getCurrUserMemos(data));
        presenter.updateUI(new UIUpdateInfo("dialog", output, "MemoListPanel"));
    }

    /**
     * Displays all memos that have corresponding memoID
     * @param memoID the ID to search for
     */
    public void displayEventsAssociatedWithMemo(String memoID) {
        List<String> events = memoManager.getEventsAssociatedWithMemo(data, Integer.parseInt(memoID));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "EventListPanel"));
    }

    /**
     * Returns the memo with this memoID
     * @param s the memo ID
     * @return
     */
    public String getMemo(String s) {
        return data.getMemoByID(Integer.parseInt(s));
    }

    /**
     * pushes the MemoOptionsPanel panel to user
     * @param rawID the memo ID
     */
    public void pushMemoOptions(String rawID){
        List<String> output = new ArrayList<>();
        output.add(rawID);
        presenter.updateUI(new UIUpdateInfo("dialog", output, "MemoOptionsPanel"));
    }

    /**
     * Pushes the "ChangeMemoPanel" to the user
     * @param input the input that the user entered
     */
    public void pushEditCurrentEvent(List<String> input){
        Event event = eventManager.getEventByID(data, Integer.parseInt(input.get(0)));
        Map<Integer, String> memos = memoManager.getMemoMapByEvent(data, event);
        List<String> output = new ArrayList<>();
        output.add("Current");
        output.add(input.get(0));
        output.add("MemoStrings");
        output.addAll(memos.values());
        output.add("MemoIDs");
        output.addAll(getKeys(memos));
        presenter.updateUI(new UIUpdateInfo("scrollable", output, "ChangeMemoPanel"));
    }

    private List<String> getKeys(Map<Integer, String> memos) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : memos.entrySet()){
            result.add(entry.getKey().toString());
        }
        return result;
    }

    /**
     * Pushes the ChangeMemoPanel panel to the user with the provided inputs
     * @param input the eventID
     */
    public void pushEditAll(List<String> input){
        Event event = eventManager.getEventByID(data, Integer.parseInt(input.get(0)));
        Map<Integer, String> memos = memoManager.getMemoMapByEvent(data, event);
        List<String> output = new ArrayList<>();
        output.add("All");
        List<String> eventIDs = getEventIDsAssociatedWithMemos(memos);
        output.addAll(eventIDs);
        output.add("MemoStrings");
        output.addAll(memos.values());
        output.add("MemoIDs");
        output.addAll(getKeys(memos));
        presenter.updateUI(new UIUpdateInfo("scrollable", output, "ChangeMemoPanel"));
    }


    private List<String> getEventIDsAssociatedWithMemos(Map<Integer, String> memos) {
        List<Event> events = data.getCurrUserEvents();
        List<String> eventIDs = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : memos.entrySet()){ // Sorry about the O(n^2) :(
            for (Event event : events){
                if (event.getMemoIDs().contains(entry.getKey())){
                    eventIDs.add(event.getID().toString());
                }
            }
        }
        return eventIDs;
    }

    /**
     * Edits the memos for the current event
     * @param eventID the ID for the event
     * @param newMemos the map of memos from which the memos will be accessed
     */
    public void editMemosCurrEvent(String eventID, Map<Integer, String> newMemos) {
        Event event = eventManager.getEventByID(data, Integer.parseInt(eventID));
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        for (Map.Entry<Integer, String> entry : newMemos.entrySet()){
            memoManager.unassociateMemoWithEvent(entry.getKey(), event);
            if (memoManager.getEventsAssociatedWithMemo(data, entry.getKey()).size() == 0){
                deleteMemo(entry.getKey().toString());
            }
            memoManager.CreateMemo(data, entry.getValue(), eventList);
        }
    }

    /**
     * Edits memos for all the events
     * @param newMemos the map of memos from which the memos will be accessed
     */
    public void editMemosAllEvents(Map<Integer, String> newMemos) {
        Map<Integer, String> memos = data.getMemos();
        for (Map.Entry<Integer, String> entry : newMemos.entrySet()){
            memos.put(entry.getKey(), entry.getValue());
            if (memoManager.getEventsAssociatedWithMemo(data, entry.getKey()).size() == 0){
                deleteMemo(entry.getKey().toString());
            }
        }
    }

    /**
     * Deleted the memo with id
     * @param s the Id of the memo to be deleted
     */
    public void deleteMemo(String s) {
        memoManager.DeleteMemo(data, Integer.parseInt(s));
    }

}
