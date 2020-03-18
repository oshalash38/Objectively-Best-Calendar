package com.group_0225.views;
import java.util.ArrayList;
import java.util.List;

/**
 * The UI for selecting events to put in a new series
 */
public class CreateSeriesEventsView extends CalendarView {
    /** Collect user's selection of which events to form into a new series
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return list of strings as user input
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i< outputs.size(); i++){
            System.out.println(i + ": " + outputs.get(i));
        }
        System.out.println("Select the events you would like to form into a new series. \n Separate your selections with commas. " +
                "(Ex. \"1,2,3\" will put events one, two and three in a new series.)");
        inputs.add(in.nextLine());
        System.out.println("What is the name of this new series?");
        inputs.add(in.nextLine());
        return inputs;

    }
}
