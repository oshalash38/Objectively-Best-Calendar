package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


/**
 * This class is responsible for displaying the UserDNEView to the user when the user entered a username and password
 * that are not in the database
 */
public class UserDNEView extends CalendarView{
    /**
     * This method displays the UserDNEView
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the input of the user
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        int input;
        System.out.println("User Does Not Exist\n1. Try again\n2. Return to startup page");
        try {
            input = Integer.parseInt(in.nextLine());
            if(!((1 <= input) && (input <= 2))){
                throw new InputMismatchException();
            }
        }catch (Exception e){
            System.out.println("Entry failed. Please Try Again.\n==================================");
            return activateView(outputs);
        }
        inputs.add(input+"");
        return inputs;
    }
}
