package com.group_0225.manager;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class receives parsed input from the controllers and manipulates entities to execute memo-related functionality
 */
public class MemoManager {

    private static int idGen = 0;
    private EventManager eventManager;

    /**
     * Creates a MemoManager instance
     */
    public MemoManager(){
        eventManager = new EventManager();

    }

    /**
     * Creates and adds a memo to an memo map
     * @param data metadata of calendar
     * @param memoValue The value of the memo
     * @return The id of the created memo
     */
    public int CreateMemo(CalendarData data, String memoValue, List<Event> events){

        int high = -1;
        for (Map.Entry<Integer, String> entry: data.getMemos().entrySet()){
            if(entry.getKey() > high){high = entry.getKey();}
        }
        idGen = high + 1;
        data.addMemo(idGen, memoValue);
        for (Event event : events){
            event.addMemoID(idGen);
        }
        data.getCurrUser().addMemo(idGen);
        return idGen;
    }


    /**
     * Deletes memo on the memo map
     * @param data The metadata of the calendar
     * @param memoId The id of the desired memo
     */
    public void DeleteMemo(CalendarData data, int memoId) {
        data.getMemos().remove(memoId);
        for (Event event : data.getCurrUserEvents()){
            if (event.getMemoIDs().contains(memoId)){
                event.removeMemoID(memoId);
            }
        }
    }

    /**
     * Getter method for all the memos associated with a particular event
     * @param event the particular event
     * @param data a CalendarData instance
     * @return the memos as a list of strings
     */
    public List<String> getMemos(Event event, CalendarData data){
        List<Integer> ids = event.getMemoIDs();
        List<String> result = new ArrayList<>();
        for (int id : ids){
            result.add(data.getMemoByID(id));
        }
        return result;
    }

    /**
     * Getter method for all the current user's memos
     * @param data a CalendarData instance
     * @return the current user's memos, as a list of strings
     */
    public List<String> getCurrUserMemos(CalendarData data){
        List<String> memos = new ArrayList<>();
        List<Integer> ids =  data.getCurrUser().getMemos();
        for (Map.Entry<Integer, String> entry : data.getMemos().entrySet()){
            if (ids.contains(entry.getKey())){
                memos.add(entry.getKey().toString());
            }
        }
        return memos;
    }

    /**
     * Getter for the ids of all events associated with a particular memo
     * @param data a CalendarData instance
     * @param memoID the id of the particular memo
     * @return the event ids, as a list of Strings
     */
    public List<String> getEventsAssociatedWithMemo(CalendarData data, Integer memoID) {
        List<String> ids = new ArrayList<>();
        List<Event> events = data.getCurrUserEvents();
        for (Event e : events){
            if (e.getMemoIDs().contains(memoID)){
                ids.add(e.getID().toString());
            }
        }
        return ids;
    }

    /**
     * Getter method for the entries in the calendarData memo hashmap that correspond to the given event
     * @param data a CalendarData instance
     * @param event an event
     * @return a HashMap of memoids to memo messages for the given event
     */
    public Map<Integer, String> getMemoMapByEvent(CalendarData data, Event event) {
        Map<Integer, String> result = new HashMap<>();
        List<Integer> memoIDs = event.getMemoIDs();
        Map<Integer, String> allMemos = data.getMemos();
        for (Map.Entry<Integer, String> entry : allMemos.entrySet()){
            if (memoIDs.contains(entry.getKey())){
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;

    }

    /**
     * Remove the given event's affiliation with a given memo
     * @param key a memo id
     * @param event the event
     */
    public void unassociateMemoWithEvent(Integer key, Event event) {
        event.removeMemoID(key);
    }
}

