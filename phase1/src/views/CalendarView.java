package views;

import java.util.List;
import java.util.Scanner;

/**
 * This class is a superclass of all the views that the user interacts with.
 */
public abstract class CalendarView {

    protected Scanner in;

    /**
     * Instantiates the Scanner in
     */
    public CalendarView() {
        in = new Scanner(System.in);
    }

    /**
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return The strings that the user entered as prompted by the specific views.
     */
    public abstract List<String> activateView(List<String> outputs);
}
