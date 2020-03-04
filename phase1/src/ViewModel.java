import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
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
    private final List<String> whichSeriesToAssociateWith = Arrays.asList("Which series would you like to associate this event with?");

    private final List<String> whichMemoToAssociateWith = Arrays.asList("Which memo would you like to associate this event with?");

    private final List<String> createUser = Arrays.asList("Enter new username:", "Enter new password:",
            "Repeat new password:");

    private final List<String> signIn = Arrays.asList("Enter username:", "Enter password:");

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



    /**
     * Creates an instance of ViewModel
     */
    public ViewModel(){}

    /**
     * Displays the menu required. These menus have numbered inputs
     */
    public void displayOptionsMenu(List<String> toDisplay) {
        for (String str : toDisplay) {
            System.out.println(str);
        }
    }

    public void displayCreateUserPT1(){
        System.out.println(createUser.get(0));
    }

    public void displayCreateUserPT2(){
        System.out.println(createUser.get(1));
    }

    public void displayCreateUserPT3(){
        System.out.println(createUser.get(2));
    }

    public void displaySignInPT1(){
        System.out.println(signIn.get(0));
    }

    public void displaySignInPT2(){
        System.out.println(signIn.get(1));
    }


    public void displayEventInfo(String[] info){
        for (int i = 0; i < eventInfo.size(); i++) {
            String str = eventInfo.get(i);
            System.out.println(str + " " + info[i]);
        }
    }

    public void pleaseTryAgain(){
        System.out.println("============================================================================================" +
                "\nPlease try again.");
    }






}
