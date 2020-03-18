package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

public class EventsView extends CalendarView {
    /** Collect, as a List of String, the event that the user wants to view
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the event that the user wants to view
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        // TODO: Handle when inputs is empty so that the return doesnt break the code.
        List<String> inputs = new ArrayList<>();
        if (outputs.size() == 0){
            System.out.println("You have no events.");
        }
        else {
            for (int i = 0; i < outputs.size()/6; i++){
                System.out.println("=======================");
                System.out.println("Event " + (i+1)+":");

                System.out.println("  The name of this event is " + outputs.get(6*i));
                System.out.println("    The name of this series is " + outputs.get(6*i+1));
                System.out.println("    This event starts at " + outputs.get(6*i+2));
                System.out.println("    This event ends at " + outputs.get(6*i+3));
                System.out.println("    This event has Alerts:\n" + outputs.get(6*i+4));
                System.out.println("  This event has tags " + outputs.get(6*i+5)+"\n");
            }
        }
        if(outputs.size() != 0) {
            System.out.println("=======================");
            System.out.println("Which event would you like to edit? type \"none\" if you don't want to edit anything or type the \"#\" of event.");
            inputs.add(in.nextLine());
        }
        return inputs;
    }
}
