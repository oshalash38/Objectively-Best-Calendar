package views;

import java.util.ArrayList;
import java.util.List;

public class CreateMemoView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("What should the memo say:");

        inputs.add(in.nextLine());

        return inputs;
    }
}
