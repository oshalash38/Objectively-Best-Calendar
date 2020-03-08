package views;

import java.util.ArrayList;
import java.util.List;

public class CreateRecurringAlertView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter message associated with the alert (enter 'null' for no message): ");
        inputs.add(in.nextLine());
        System.out.println("For the start date and time:");
        inputs.addAll(new CreateDateTimeView().activateView(null));
        System.out.println("For frequency of alert: ");
        System.out.println("Enter number of days: ");
        inputs.add(in.nextLine());
        System.out.println("Enter number of hours: ");
        inputs.add(in.nextLine());
        System.out.println("Enter number of minutes: ");
        inputs.add(in.nextLine());
        return inputs;
    }
}