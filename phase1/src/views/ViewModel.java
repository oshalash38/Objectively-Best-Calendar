package views;

import java.util.*;

/**
 * This class will be deleted.
 *
 * @author Daniel Shoichet
 *
 */

public class ViewModel {

    public Map<UIViews, CalendarView> views;

    public ViewModel(){
        views = new HashMap();
        views.put(UIViews.createUser, new CreateNewUserView());
        views.put(UIViews.eventInfo, new EventView());
        views.put(UIViews.startup, new MenuView("CALENDAR V1\n===============" +
                        "\n1. Login\n2. Create new user\n3. Exit", 3));
        views.put(UIViews.mainMenu, new MenuView(
                 "Main Menu:\nCurrent alerts:\n1. Check upcoming alerts\n2. Create memo\n3. Create event" +
                         "\n4. Create series\n5. Event options\n6. Display events filtered by..." +
                         "\n7. Logout", 7));
        views.put(UIViews.displayEventBy, new MenuView(
                "Display events filtered by...\n1. Current events\n2. Past events\n3. Future events" +
                        "\n4. Date threshold\n5. Memo\n6. Tag\n7. Name\n8. Series\n9. Return", 9));
        views.put(UIViews.eventType, new MenuView("What event type would you like to create?" +
                        "\n1. Regular event\n2. Series of events\n3.Return", 3));
        views.put(UIViews.userDNE, new MenuView("User Does Not Exist\n1. Try again" +
                "\n2. Return to startup page", 2));
        views.put(UIViews.eventOptions, new MenuView("What event type would you like to create?\n" +
                "1. Regular event\n2. Series of events\n3, Return to main menu", 3));

        views.put(UIViews.createDateTimeView, new CreateDateTimeView());
        views.put(UIViews.createSeriesScratch, new CreateSeriesScratchView());
        /*TODO (Peter) Does menu really need parameters? Can't we pass the userOptions via Controller -> Presenter ->
            -> activateView(userOptions). This might be better since views like ChooseFrequency and CreateDate are used
            by other views not by presenter directly
         */
        views.put(UIViews.chooseFrequency, new MenuView("What is the frequency of events" +
                "in this series?\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly", 5));

        views.put(UIViews.loginView, new LoginView());
        views.put(UIViews.createEvent, new CreateNewEventView());

    }


    private final List<String> whichSeriesToAssociateWith = Arrays.asList("Which series would you like to associate this event with?");

    private final List<String> whichMemoToAssociateWith = Arrays.asList("Which memo would you like to associate this event with?");

    private final List<String> signIn = Arrays.asList("Enter username:", "Enter password:");

    private final List<String> selectEventsForSeries = Arrays.asList("Enter the numbers " +
            "of the events that will form this series. Separate them by commas (ex. 1,2,3 to select the first three events)");


    private final List<String> createEventAssociatedWithSeries = Arrays.asList("Enter event name:",
            "Enter event start time:", "Enter event end time:", "Enter name of series:" ,
            "Type in frequency of series events \n Options are: hourly, daily, weekly, monthly, yearly:",
            "Would you like to be reminded when this event begins?\nOptions are: yes, no:");

    private final List<String> typeAlert = Arrays.asList("Enter name of this alert (type none if you don't want a name associated with this alert):"
            ,"Is this a one time alert or a repeating alert?", "1. One time", "2. Repeating");

    private final List<String> repeatingAlert = Arrays.asList("When should this alert begin notifying you?",
            "How frequently would you like to be notified? To control this, enter the next time the program should notify you and the program will calculate the delta.");

    private final List<String> oneTimeAlert = Arrays.asList("When should this alert begin notifying you?");

    private final List<String> alertInfo = Arrays.asList("Name of alert:", "Repeating alert or one time alert:",
            "Frequency of alert:", "Next notification by alert:");

    private final List<String> eventInfo = Arrays.asList("Event name:", "Event start time:", "Event end time:",
            "Event series:" , "Event tag:", "Event memos:", "Event reminders:");

    private final List<String> filterEventByRequiresSearch = Arrays.asList("Type name of ", " you are looking for:");


}
