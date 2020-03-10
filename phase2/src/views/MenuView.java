package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


/**
 * This class is responsible for displaying the different menus to the user
 */
public class MenuView extends CalendarView{

    private String userOptions;
    private int numOptions;

    /**
     *
     * @param userOptions the text that is displayed to the user as the options
     * @param numOptions
     */
    public MenuView(String userOptions, int numOptions){
        this.userOptions = userOptions;
        this.numOptions = numOptions;
    }


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
        System.out.println(userOptions);
        try {
            input = Integer.parseInt(in.nextLine());
            if(!((1 <= input) && (input <= numOptions))){
                throw new InputMismatchException();
            }
        }catch (Exception e){
            System.out.println("Entry failed. Please Try Again.\n==================================");
            return activateView(outputs);
        }
        inputs.add(input+"");
        return inputs;
    }

    public String getUserOptions() {
        return userOptions;
    }
    public int getNumOptions() {
        return numOptions;
    }
}