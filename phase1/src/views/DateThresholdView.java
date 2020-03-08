package views;

import java.util.ArrayList;
import java.util.List;

public class DateThresholdView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        System.out.println("Find events between 2 dates:");
        System.out.println("-----------------------------");
        CreateDateTimeView dt = new CreateDateTimeView();
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter first date and time:");
        inputs.addAll(dt.activateView(null));
        System.out.println("Enter second date and time:");
        inputs.addAll(dt.activateView(null));
        return inputs;
    }
}
