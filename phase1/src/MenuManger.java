import java.util.Arrays;
import java.util.List;

/**
 * This class stores all the menus that are presented to user.
 *
 * @author Daniel Shoichet
 */
public class MenuManger {
    private final String enterOption= "Enter choice:";

    Menu startUpPage = new Menu(Arrays.asList("CALENDAR V1", "===============", "1. Login",
            "2. Create new user", "3. Exit", enterOption), 3);

    Menu userDNE = new Menu(Arrays.asList("User Does Not Exist", "1. Try again",
            "2. Return to startup page", enterOption), 2);

    Menu mainMenu = new Menu(Arrays.asList("Main Menu:", "Current alerts:", "1. Check upcoming alerts",
            "2. Create memo", "3. Create event", "4. Create series", "5. Event options",
            "6. Display events filtered by...", "7. Logout" , enterOption), 7);

    Menu eventType = new Menu(Arrays.asList("What event type would you like to create?",
            "1. Regular event", "2. Series of events" , enterOption), 2);

    Menu eventOptions = new Menu(Arrays.asList("1. Delete event", "2. Associate event with memo",
            "3. Create alert for event", "4. View alerts for event", "5. Create tag for event",
            "6. Associate event with series", "7. return",
            enterOption), 7);

    Menu displayEventBy = new Menu(Arrays.asList("Display events filtered by...", "1. Current events",
            "2. Past events", "3. Future events", "4. Date threshold", "5. Memo", "6. Tag", "7. Name", "8. Series",
            "9. Return", enterOption), 9);


    /**
     *
     * @return the startUpPage option menu
     */
    public Menu getStartUpPage() {
        return startUpPage;
    }

    /**
     *
     * @return the userDNE option menu
     */
    public Menu getUserDNE() {
        return userDNE;
    }

    /**
     *
     * @return the mainMenu
     */
    public Menu getMainMenu() {
        return mainMenu;
    }

    /**
     *
     * @return the eventType option menu
     */
    public Menu getEventType() {
        return eventType;
    }

    /**
     *
     * @return the eventOptions option menu
     */
    public Menu getEventOptions() {
        return eventOptions;
    }

    /**
     *
     * @return the enter displayEventBy menu
     */
    public Menu getDisplayEventBy() {
        return displayEventBy;
    }
}
