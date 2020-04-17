package com.group_0225.manager;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Event;

import java.util.*;

public class MemoManager {

    private static int idGen = 0;
    private EventManager eventManager;

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

    public int associateMemo(Map<Integer, String> memos, String memoValue, Event e){
        int temp = -1;
        for (Map.Entry<Integer, String> entry: memos.entrySet()){
            if (entry.getValue().equals(memoValue)){
                e.addMemoID(entry.getKey());
                temp = entry.getKey();
                break;
            }
        }
        return temp;
    }
    public List<String> DisplayAllMemos(Map<Integer, String> map){
        List<String> retList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry: map.entrySet()){
            retList.add(entry.getKey() + ":" + entry.getValue());
        }
        return retList;
    }

    /**
     * Formats memo output
     * @param memos The memo map from User
     * @param memoId The id of the desire memo
     * @return The formatted memo output
     */
    public String DisplayMemo(Map<Integer, String> memos, int memoId) {
        String memoValue = memos.get(memoId);

        return "Memo: " + memoValue;
    }

    /**
     * Filters the events by id
     * @param events The list of events from User
     * @param memoId The id of the desired memo
     * @return A list of events that are attached to the memo
     */
    public List<Event> FilterByMemoId(List<Event> events, int memoId) {
        List<Event> filtered = new ArrayList();

        for(Event e : events)
            if (e.getMemoIDs().contains(memoId))
                filtered.add(e);

        return filtered;
    }

    /**
     * Alters a memo on the memo map
     * @param memos The memo map from User
     * @param memoValue The value of the memo
     * @param memoId The id of the desired memo
     */
    public void AlterMemo(Map<Integer, String> memos, String memoValue, int memoId){
        memos.put(memoId, memoValue);
    }

    /**
     * Deletes memo on the memo map
     * @param data The metadata of the calendar
     * @param memoId The id of the desired memo
     */
    public void DeleteMemo(CalendarData data, int memoId) {
        data.getMemos().remove(memoId);
    }

    public List<String> getMemos(Event event, CalendarData data){
        List<Integer> ids = event.getMemoIDs();
        List<String> result = new ArrayList<>();
        for (int id : ids){
            result.add(data.getMemoByID(id));
        }
        return result;
    }

    public List<String> getCurrUserMemos(CalendarData data){
        List<String> memos = new ArrayList<>();
        List<Integer> ids =  data.getCurrUser().getMemos();
        System.out.println("NUM OF MEMOS: " + data.getMemos().size());
        System.out.println("curr user id size:" + ids.size());
        for (Map.Entry<Integer, String> entry : data.getMemos().entrySet()){
            if (ids.contains(entry.getKey())){
                memos.add(entry.getKey().toString());
                System.out.println("Im an iteration");
            }
        }
        return memos;
    }

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

    public void unassociateMemoWithEvent(Integer key, Event event) {
        event.removeMemoID(key);
    }
}

