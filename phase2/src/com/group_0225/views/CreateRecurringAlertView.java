package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

/**
 * Allow user to create a new recurring alert
 */
public class CreateRecurringAlertView extends CalendarView {
    /**Collect user preferences on a new recurring alert
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return user inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter message associated with the alert (enter 'null' for no message): ");
        inputs.add(in.nextLine());
        System.out.println("For the start date and time:");
        inputs.addAll(new CreateDateTimeView().activateView(null));
        System.out.println("For frequency of alert: ");
        System.out.println("Enter number of days: ");
        inputs.add(in.nextLine());
        System.out.println("Enter number of hours: ");
        inputs.add(in.nextLine());
        System.out.println("Enter number of minutes: ");
        inputs.add(in.nextLine());
        return inputs;
    }
}
