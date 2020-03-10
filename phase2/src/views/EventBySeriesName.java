package views;

import java.util.ArrayList;
import java.util.List;

public class EventBySeriesName extends CalendarView {
    /** Collects user input for which series they would like to view
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return list of strings of user input
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("What series would you like to display?");
        inputs.add(in.nextLine());
        return inputs;
    }
}
