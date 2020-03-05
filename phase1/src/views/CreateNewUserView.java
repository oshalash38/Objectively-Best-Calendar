package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class is responsible for the view of CreateNewUser
 */
public class CreateNewUserView extends CalendarView {
    /**
     *
     * @param outputs the items that need to be displayed to the user. In this case none.
     * @return
     */
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter new username:");
        inputs.add(in.nextLine());
        System.out.println("Enter new password:");
        inputs.add(in.nextLine());

        return inputs;
    }

}
