package views;

import java.util.ArrayList;
import java.util.List;

public class CreateMemoView extends CalendarView {
    /**Collect user preferences on creating a memo and associating it with one or more events.
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
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
