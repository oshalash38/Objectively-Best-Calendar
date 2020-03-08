package views;

import java.util.ArrayList;
import java.util.List;

public class EditMemoView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Memo:\n" + outputs.get(0));

        return inputs;
    }
}
