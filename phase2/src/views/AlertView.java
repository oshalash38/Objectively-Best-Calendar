package views;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Allow user to see that they have new notifications
 */
public class AlertView extends CalendarView{
    /**Notify user of new notifications
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        for(String s: outputs){
            System.out.println(s);
        }
        System.out.println("==========================================================");
        return inputs;
    }
}
