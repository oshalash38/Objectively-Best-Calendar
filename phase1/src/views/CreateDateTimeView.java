package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for the view of CreateNewUser
 */
public class CreateDateTimeView extends CalendarView {
    /**
     *
     * @param outputs the items that need to be displayed to the user. In this case none.
     * @return The entries of the user. In this case the username and password
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter year:");
        inputs.add(in.nextLine());
        System.out.println("Enter month number: (1-12)");
        inputs.add(in.nextLine());
        System.out.println("Enter day of month: (1-31)");
        inputs.add(in.nextLine());
        System.out.println("Enter hour: (24 hour clock)");
        inputs.add(in.nextLine());
        System.out.println("Enter minute: (0-59)");
        inputs.add(in.nextLine());
        return inputs;
    }
}