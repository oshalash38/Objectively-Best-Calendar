package views;
import views.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class ChangeName extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter new name:");
        inputs.add(in.nextLine());
        return inputs;
    }
}