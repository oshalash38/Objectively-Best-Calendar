import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class displays information to the user.
 *
 * @author Daniel Shoichet
 *
 */

public class ViewModel {
    private final String enterOption= "Enter choice:";
    private final List<String> startUpPage = Arrays.asList("CALENDAR V1", "===============", "1. Login", "2. Create new user", enterOption);
    //TODO CONVERT EVERYTHING TO LIST SORRY WHOEVER IS LOOKING AT THIS I GOTTA GO BUT I'LL FIX IT
    private final String[] createUser = {"Enter new username:", "Enter new password:"};
    private final String[] signIn = {"Enter username:", "Enter password:"};
    private final String[] mainMenu = {"Main Menu:", "Current alerts:", "1. Check upcoming alerts", "2. Create memo",
    "3. Create event", "4. Create series", "5. Event options", "6. Display events filtered by...", "7. Logout" , enterOption};
    private final String[] eventOptions = {"1. Delete event", "2. Associate event with memo",
            "3. Create alert for event", "4. View alerts for event", "5. Create tag for event", "6. return",
            enterOption};
    private final String[] eventInfo = {"Event name:", "Event time:", "Event tag:", "Event memos:", "Event reminders:"};
    private final String[] displayEventBy = {"Display events filtered by...", "1. Current events", "2. Past events",
            "3. Future events", "4. Date threshold", "5. Memo", "6. Tag", "7. Return", enterOption};


    /**
     * Creates an instance of ViewModel
     */
    public ViewModel(){}

    public void startUp(){

    }


}
