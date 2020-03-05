package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CreateNewUserView extends CalendarView {
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter new username:");
        inputs.add(in.nextLine());
        System.out.println("Enter new password:");
        inputs.add(in.nextLine());

        return inputs;
    }

}
