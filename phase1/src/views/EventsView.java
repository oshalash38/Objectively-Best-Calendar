package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EventsView extends CalendarView {
    public static int counter = 0;
    @Override
    public List<String> activateView(List<String> outputs) {
        // TODO: Handle when inputs is empty so that the return doesnt break the code.
        List<String> inputs = new ArrayList<>();
        if (outputs.size() == 0){
            System.out.println("You have no events.");
        }
        else {
            for (int i = 0; i < outputs.size(); i++){
                System.out.println("=======================");
                System.out.println((i +1)+ ":" + outputs.get(i));
            }
        }
        System.out.println("=======================");
        System.out.println("Which event would you like to edit?");
        inputs.add(in.nextLine());

        return inputs;
    }
}
