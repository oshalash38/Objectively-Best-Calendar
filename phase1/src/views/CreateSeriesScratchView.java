package views;

import java.util.ArrayList;
import java.util.List;

/**
 * The UI for creating a series from scratch
 * (i.e. when the user enters frequency, duration, number of events, series name)
 * @author Peter Sellars
 * {@link ViewModel#ViewModel()}
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
    @Override

    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        System.out.println("Choose your new series' properties.\n What is this series' name?");
        inputs.add(in.nextLine());

        //CREATE DATE TIME VIEW
        System.out.println("What is the start time of this new series?");
        CreateDateTimeView cdtv = new CreateDateTimeView();
        inputs.addAll(cdtv.activateView(null));

        //CHOOSE FREQUENCY VIEW
//        System.out.println("How often would you like this series to occur?");

        ChooseFrequencyMenu cfm = new ChooseFrequencyMenu(outputs.get(0), 5);
        inputs.addAll(cfm.activateView());
        System.out.println("What is the duration of each event in this series?");
        CreateDurationView cdv = new CreateDurationView();
        inputs.addAll(cdv.activateView(null));
        System.out.println("How many events are in this series?");
        inputs.add(in.nextLine());
        System.out.println("=================================================");
        return inputs;
    }
}
