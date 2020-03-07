package views;

import java.util.ArrayList;
import java.util.List;

public class CreateNewEventView extends CalendarView{
    @Override
    public List<String> activateView(List<String> outputs) {
        // TODO: Implement validation check for format.
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter event name:");
        inputs.add(in.nextLine());
        System.out.println("Enter event start time and date (Format: YYYY/MM/DD/Hour:Minute)");
        inputs.add(in.nextLine());
        System.out.println("Enter event end time and date (Format: YYYY/MM/DD/Hour:Minute)");
        inputs.add(in.nextLine());
        System.out.println("Event added");
        return inputs;
    }
}
