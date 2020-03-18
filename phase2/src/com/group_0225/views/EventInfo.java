package com.group_0225.views;

import java.util.List;

/**
 * This class displays all the possible information with an event
 */

public class EventInfo extends CalendarView{
    /**
     * @param outputs the strings that need to be displayed to the user.
     * @return The strings that the user entered as prompted by the specific views.
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        System.out.println("The name of this event is " + outputs.get(0));
        System.out.println("  The name of this series is " + outputs.get(1));
        System.out.println("  This event starts at " + outputs.get(2));
        System.out.println("  This event ends at " + outputs.get(3));
        System.out.println("  This event has Alerts:\n" + outputs.get(4));
        System.out.println(" This event has tags " + outputs.get(5)+"\n");
        return null;
    }
}
