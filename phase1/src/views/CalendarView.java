package views;

import java.util.List;
import java.util.Scanner;

public abstract class CalendarView {

    protected Scanner in;

    public CalendarView() {
        in = new Scanner(System.in);
    }

    public abstract List<String> activateView(List<String> outputs);
}
