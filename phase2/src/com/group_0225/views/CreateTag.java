package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

public class CreateTag extends CalendarView {
    /** Collect input of user's desired tag
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return list of strings of user input
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter name of tag:");
        if(outputs != null){
            System.out.println("A new tag will be created if it does not exists.");
        }
        inputs.add(in.nextLine());
        return inputs;
    }
}