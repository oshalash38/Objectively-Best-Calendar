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
    private final String enterOption= "Enter choice:";
    private final List<String> startUpPage = Arrays.asList("CALENDAR V1", "===============", "1. Login",
            "2. Create new user", enterOption);
    private final List<String> createUser = Arrays.asList("Enter new username:", "Enter new password:",
            "Repeat new password:");
    private final List<String> signIn = Arrays.asList("Enter username:", "Enter password:");
    private final List<String> userDNE = Arrays.asList("User Does Not Exist", "1. Try again",
            "2. Return to startup page", enterOption);
    private final List<String> mainMenu = Arrays.asList("Main Menu:", "Current alerts:", "1. Check upcoming alerts",
            "2. Create memo", "3. Create event", "4. Create series", "5. Event options",
            "6. Display events filtered by...", "7. Logout" , enterOption);
    private final List<String> eventOptions = Arrays.asList("1. Delete event", "2. Associate event with memo",
            "3. Create alert for event", "4. View alerts for event", "5. Create tag for event",
            "6. Associate event with series", "6. return",
            enterOption);

    private final List<String> typeAlert = Arrays.asList("Enter name of this alert (type none if you don't want a name associated with event):"
            ,"Is this a one time alert or a repeating alert?", "1. One time", "2. Repeating");
    private final List<String> repeatingAlert = Arrays.asList("When should this alert begin notifying you?",
            "How frequently would you like to be notified?", "Enter days between each notification:",
            "Enter hours between each notification:");
    private final List<String> oneTimeAlert = Arrays.asList("Enter when this alert should notify you:");
    private final List<String> buildTiming = Arrays.asList("Enter year:", "Enter month:", "Enter day of month:",
            "Enter hour (24 hour clock):", "Enter minute:");

    private final List<String> alertInfo = Arrays.asList("Name of alert:", "Repeating alert or one time alert:",
            "Frequency of alert:", "Next notification by alert:") ;

    private final List<String> eventInfo = Arrays.asList("Event name:", "Event start time:", "Event end time:",
            "Event series:" , "Event tag:", "Event memos:", "Event reminders:");
    private final List<String> displayEventBy = Arrays.asList("Display events filtered by...", "1. Current events",
            "2. Past events", "3. Future events", "4. Date threshold", "5. Memo", "6. Tag", "7. Name", "8. Return",
            enterOption);



    /**
     * Creates an instance of ViewModel
     */
    public ViewModel(){}

    public void displayStartUp(){
        for (String str: startUpPage){
            System.out.println(str);
        }
    }

    public void displayMainMenu(){
        for(String str: mainMenu){
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

    public void displayUserDNE(){
        for(String str: userDNE){
            System.out.println(str);
        }
    }

    public void displayEventOptions(){
        for(String str: eventOptions){
            System.out.println(str);
        }
    }

    public void displayEventInfo(String[] info){
        int counter = 0;
        for(String str: eventInfo){
            System.out.println(str + " " + info[counter]);
            counter++;
        }
    }

    public void displayDisplayEventsBy(){
        for(String str: displayEventBy){
            System.out.println(str);
        }
    }





}
