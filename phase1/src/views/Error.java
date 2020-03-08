package views;

import java.util.List;

public class Error extends CalendarView{
    @Override
    public List<String> activateView(List<String> outputs) {
        System.out.println("Error please try again");

        return null;
    }
}
