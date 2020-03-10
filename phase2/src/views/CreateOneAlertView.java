package views;

import java.util.ArrayList;
import java.util.List;

public class CreateOneAlertView extends CalendarView {
    /**Collect user preferences on a new alert
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter message associated with the alert (enter 'null' for no message): ");
        inputs.add(in.nextLine());
        System.out.println("For the date and time of alert:");
        inputs.addAll(new CreateDateTimeView().activateView(null));
        return inputs;
    }
}
