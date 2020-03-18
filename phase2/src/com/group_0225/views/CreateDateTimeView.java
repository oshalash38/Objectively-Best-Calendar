package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the view of CreateNewUser
 */
public class CreateDateTimeView extends CalendarView {
    /**
     *
     * @param outputs the items that need to be displayed to the user. In this case none.
     * @return The entries of the user. In this case the username and password
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        try{
            System.out.println("Enter year:");
            inputs.add(in.nextLine());
            Integer.parseInt(inputs.get(0));

            System.out.println("Enter month number: (1-12)");
            inputs.add(in.nextLine());
            validInteger(inputs.get(1), 1, 12);

            System.out.println("Enter day of month: (1-31)");
            inputs.add(in.nextLine());
            validInteger(inputs.get(2), 1, 31);

            System.out.println("Enter hour: (24 hour clock)");
            inputs.add(in.nextLine());
            validInteger(inputs.get(3), 0, 23);

            System.out.println("Enter minute: (0-59)");
            inputs.add(in.nextLine());
            validInteger(inputs.get(4), 0, 59);

        } catch (Exception e) {
            System.out.println("\n======INVALID INPUT! PLEASE RETRY!=====\n\n");
            return activateView(outputs);
        }

        return inputs;
    }

    public void validInteger(String input, int min, int max) throws Exception {
        int num = Integer.parseInt(input);

        if(num < min || num > max)
            throw new Exception();
    }
}