package views;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for user searching his events
 */
public class SearchEvents extends CalendarView {
    /**Collect user preferences on which event should be displayed
     *
     * @param outputs the items that need to be displayed to the user. In this case none.
     * @return The entries of the user. In this case the search term
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("What event would you like to find:");
        inputs.add(in.nextLine());
        return inputs;
    }
}
