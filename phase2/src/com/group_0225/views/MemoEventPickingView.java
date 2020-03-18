package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

public class MemoEventPickingView extends CalendarView {
    /**Collect user preferences for which events to associate with a given memo
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Events:");

        for(int i = 0; i < outputs.size(); i++){
            System.out.println(i + ": " + outputs.get(i));
        }
        System.out.println("Choose one or more events to associate with (-1 to end):");

        String choice = in.nextLine();

        try{
            if (Integer.parseInt(choice) < -1 || Integer.parseInt(choice) >= outputs.size())
                throw new Exception();
        } catch (Exception e) {
            System.out.println("\n=========PLEASE TRY AGAIN==========\n\n");
            return activateView(outputs);
        }

        while (Integer.parseInt(choice) != -1 && inputs.size() < outputs.size() - 1){
            inputs.add(choice);
            choice = in.nextLine();
        }


        return inputs;
    }
}
