package views;

import java.util.ArrayList;
import java.util.List;

public class MemoMenuView extends CalendarView {
    /**Collect user preferences on viewing memos and their associated events
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the user's inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        if (outputs.size() == 0){
            System.out.println("You have no memos");
        } else {
            int i = 1;
            for (String memoName : outputs){
                System.out.println(memoName);
                System.out.println("--------------------------------");
            }
            System.out.println("Do you want to see events associated with a memo? (Yes/No)");
            if (in.nextLine().toLowerCase().equals("yes")){
                System.out.println("Choose a memo to see associations:");
                inputs.add(in.nextLine());
            }
        }
        return inputs;
    }
}
