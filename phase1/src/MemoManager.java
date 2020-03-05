import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MemoManager {

    private Random idGen;

    public MemoManager(){
        idGen = new Random();
    }

    /**
     * Creates and adds a memo to an memo map
     * @param memos The memo map from User
     * @param memoValue The value of the memo
     * @return The id of the created memo
     */
    public int CreateMemo(Map<Integer, String> memos, String memoValue){
        int id = idGen.nextInt(Integer.MAX_VALUE);

        while(memos.containsKey(id))
            id = idGen.nextInt(Integer.MAX_VALUE);

        memos.put(id, memoValue);

        return id;
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
     * @param memos The memo map from User
     * @param memoId The id of the desired memo
     */
    public void DeleteMemo(Map<Integer, String> memos, int memoId) {
        memos.remove(memoId);
    }
}
