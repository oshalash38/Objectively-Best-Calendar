package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EventView extends CalendarView {

    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println(outputs.get(0));
        System.out.println(outputs.get(1));

        Integer.parseInt(in.nextLine());


        return inputs;
    }
}
