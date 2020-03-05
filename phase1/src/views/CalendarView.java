package views;

import java.util.List;
import java.util.Scanner;

/**
 *
 */

public abstract class CalendarView {

    protected Scanner in;

    public CalendarView() {
        in = new Scanner(System.in);
    }

    /**
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return The strings that the user entered as prompted by the specific vies.
     */
    public abstract List<String> activateView(List<String> outputs);
}
