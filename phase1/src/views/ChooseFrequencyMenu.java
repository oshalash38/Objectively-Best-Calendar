package views;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the user to choose a frequency for their new series
 * @author Peter Sellars
 */
public class ChooseFrequencyMenu extends MenuView {
    /**
     * @param userOptions the text that is displayed to the user as the options
     * @param numOptions the user has five frequencies to choose from
     */
    public ChooseFrequencyMenu(String userOptions, int numOptions) {
        super(userOptions, numOptions);
    }

    //@Override
    public List<String> activateView() {
        List<String> inputs = new ArrayList<>();
        //TODO REPLACE WITH LIST.GET(), consult with Daniel
        //System.out.println(this.getUserOptions());
        System.out.println("How often do you want your events to occur?");
        System.out.println("Hourly, Daily, Weekly, Monthly, Yearly");
        inputs.add(in.nextLine());
        return inputs;
    }
}
