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
    public int CreateMemo(Map<Integer, Memo> memos, String memoValue){
        int id = idGen.nextInt(Integer.MAX_VALUE);

        while(memos.containsKey(id))
            id = idGen.nextInt(Integer.MAX_VALUE);

        Memo newMemo = new Memo(id, memoValue);

        memos.put(id, newMemo);

        return id;
    }

    //Todo figure out what belongs in memo manager and what is going in user
}
