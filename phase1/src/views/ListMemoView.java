package views;

import java.util.ArrayList;
import java.util.List;

public class ListMemoView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        if (outputs == null){
            System.out.println("No memos exist yet");
            return null;
        }

        System.out.println("Which memo would you like to associate this event with:");


        for(int i = 1; i <= outputs.size(); i++){
            System.out.println(outputs.get(i-1));
        }

        try{
            int choice = Integer.parseInt(in.nextLine());
            inputs.add(choice+"");
        } catch (Exception e) {
            System.out.println("\n=========PLEASE TRY AGAIN==========\n\n");
            return activateView(outputs);
        }
        return inputs;
    }
}
