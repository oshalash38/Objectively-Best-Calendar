package views;

import java.util.ArrayList;
import java.util.List;

/**
 * Allow user to change the name
 */
public class ChangeName extends CalendarView {
    /** Collect as a List of Strings the user's selection for a new name
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the user's selection for a new name
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter new name:");
        inputs.add(in.nextLine());
        return inputs;
    }
}