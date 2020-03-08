package views;

import java.util.ArrayList;
import java.util.List;

public class CreateMemoView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Enter message of memo: ");

        inputs.add(in.nextLine());

        System.out.println("Associate with one or more events:");

//        if (outputs.size() == 0){
//            System.out.println("You have no events.");
//        }
//        else {
//            for (int i = 0; i < outputs.size(); i++){
//                if (i % 3 == 0){
//                    System.out.println("=======================");
//                }
//                System.out.println(outputs.get(i));
//            }
//        }

        return inputs;
    }
}
