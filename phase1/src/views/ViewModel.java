package views;

import java.util.*;

/**
 * This class displays information to the user.
 *
 * @author Daniel Shoichet
 *
 */

public class ViewModel {

    public Map<MyEnum, CalendarView> views;

    public ViewModel(){
         views = new HashMap();

         views.put(MyEnum.startup, new StartupView());
         views.put(MyEnum.createUser, new CreateNewUserView());
        views.put(MyEnum.eventInfo, new EventView());
    }
    private final List<String> whichSeriesToAssociateWith = Arrays.asList("Which series would you like to associate this event with?");

    private final List<String> whichMemoToAssociateWith = Arrays.asList("Which memo would you like to associate this event with?");

    private final List<String> createUser = Arrays.asList("Repeat new password:", "Enter new password:", "Enter new username:");

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

    private final List<String> buildTiming = Arrays.asList("Enter year:", "Enter month number:", "Enter day of month:",
            "Enter hour (24 hour clock):", "Enter minute:");

    private final List<String> alertInfo = Arrays.asList("Name of alert:", "Repeating alert or one time alert:",
            "Frequency of alert:", "Next notification by alert:");

    private final List<String> eventInfo = Arrays.asList("Event name:", "Event start time:", "Event end time:",
            "Event series:" , "Event tag:", "Event memos:", "Event reminders:");

    private final List<String> filterEventByRequiresSearch = Arrays.asList("Type name of ", " you are looking for:");

}
