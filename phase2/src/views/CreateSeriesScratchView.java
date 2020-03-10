package views;

import java.util.ArrayList;
import java.util.List;

/**
 * The UI for creating a series from scratch
 * (i.e. when the user enters frequency, duration, number of events, series name)
 * @author Peter Sellars
 * 0: SeriesName
 * 1: Start year
 * 2: Start month
 * 3: Start day
 * 4: Start hour
 * 5: Start minute
 * 6: Frequency selection
 * 7: Day duration
 * 8: Hour duration
 * 9: Minute duration
 * 10: number of events
 */
public class CreateSeriesScratchView extends CalendarView{

    /**Collects user's preferences for creating a series from scratch.
     * @param outputs null
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Choose your new series' properties.\n What is this series' name?");
        inputs.add(in.nextLine());

        System.out.println("What is the start time of this new series?");
        CreateDateTimeView cdtv = new CreateDateTimeView();
        inputs.addAll(cdtv.activateView(null));
        MenuView cfm = new MenuView("What is the frequency of events" +
                " in this series?\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly", 5);
        inputs.addAll(cfm.activateView(null));
        System.out.println("What is the duration of each event in this series?");
        CreateDurationView cdv = new CreateDurationView();
        inputs.addAll(cdv.activateView(null));
        System.out.println("How many events are in this series? There must be a minimum of 2 events in all series.");
        inputs.add(in.nextLine());
        System.out.println("=================================================");
        return inputs;
    }
}
