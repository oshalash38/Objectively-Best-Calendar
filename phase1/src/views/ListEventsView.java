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

        try{
            int choice = Integer.parseInt(in.nextLine());
            if (choice < 0 || choice >= outputs.size())
                throw new Exception();
        } catch (Exception e) {
            System.out.println("\n=========PLEASE TRY AGAIN==========\n\n");
            return activateView(outputs);
        }
        return inputs;
    }
}
