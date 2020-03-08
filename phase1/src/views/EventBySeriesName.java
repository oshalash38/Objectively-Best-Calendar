package views;

import java.util.ArrayList;
import java.util.List;

public class EventBySeriesName extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("What series would you like to display?");
        inputs.add(in.nextLine());
        return inputs;
    }
}
