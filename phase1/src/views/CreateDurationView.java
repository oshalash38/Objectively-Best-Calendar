package views;

import java.util.ArrayList;
import java.util.List;

public class CreateDurationView extends CalendarView{
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Choose the duration:");
        System.out.println("Number of days (0-6)");
        inputs.add(in.nextLine());
        System.out.println("Number of hours (0-23)");
        inputs.add(in.nextLine());
        System.out.println("Number of minutes (0-59)");
        inputs.add(in.nextLine());
        return inputs;

    }
}
