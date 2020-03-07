package views;

import java.util.ArrayList;
import java.util.List;

public class ListEventsView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Events:");

        for(int i = 0; i < outputs.size(); i++){
            System.out.println(i + ": " + outputs.get(i));
        }

        inputs.add(in.nextLine());
        return inputs;
    }
}
