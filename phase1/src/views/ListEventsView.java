package views;

import java.util.ArrayList;
import java.util.List;

public class ListEventsView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Which event would you like to manipulate:");

        for(int i = 1; i <= outputs.size(); i++){
            System.out.println(i + ": " + outputs.get(i-1));
        }

        try{
            int choice = Integer.parseInt(in.nextLine());
            if (choice <= 0 || choice > outputs.size())
                throw new Exception();
            inputs.add(choice+"");
        } catch (Exception e) {
            System.out.println("\n=========PLEASE TRY AGAIN==========\n\n");
            return activateView(outputs);
        }
        return inputs;
    }
}