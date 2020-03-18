package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

public class CreateDurationView extends CalendarView{
    /**Collect user preferences on the duration of an event
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Choose the duration:");
        System.out.println("Number of days (0-6)");
        inputs.add(in.nextLine());
        System.out.println("Number of hours (0-23)");
        inputs.add(in.nextLine());
        System.out.println("Number of minutes (0-59)");
        inputs.add(in.nextLine());
        return inputs;

    }
}
