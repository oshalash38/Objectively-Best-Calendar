package views;

import java.util.ArrayList;
import java.util.List;

public class CreateNewEventView extends CalendarView{
    /**Collect the user's preferences for a new event creation
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the user's inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        // TODO: Implement validation check for format.

        CreateDateTimeView dt = new CreateDateTimeView();
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter event name:");
        inputs.add(in.nextLine());
        System.out.println("Enter event start time and date:");
        inputs.addAll(dt.activateView(null));
        System.out.println("Enter event end time and date:");
        inputs.addAll(dt.activateView(null));
        System.out.println("Would you like to be reminded when this event begins?\nOptions are: yes, no:");
        inputs.add(in.nextLine());
        return inputs;
    }
}
