import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * This class controls the information that is pushed to the user.
 *
 * @author Daniel Shoichet
 */
public class Presenter {
    private ViewModel myModel = new ViewModel();
    private MenuManger myMenus = new MenuManger();

    /**
     * Instantiates a Presenter class
     */
    public Presenter(){ }


    /**
     * Display the startup page
     *
     * #TODO THIS METHOD HAS DUPLICATE CODE NEED TO FIND WAY TO REMOVE IT
     *
     * @return 1 if the user selected to login or 2 if the user selected to create a new account
     */
    public int menuSelection(Menu menu){
        int input = 0;
        myModel.displayOptionsMenu(menu.getContents());
        try {
            Scanner kbReader = new Scanner(System.in);
            input = kbReader.nextInt();
        }catch (InputMismatchException e){
            myModel.pleaseTryAgain();
            return menuSelection(menu);
        }
        if(!((1 <= input) && (input <= menu.getNumOptions()))){
            myModel.pleaseTryAgain();
            return menuSelection(menu);
        }
        return input;
    }

    public int getStartUpPageInput(){
        return menuSelection(myMenus.getStartUpPage());
    }


//    public int startupPage(){
//
//    }
    private String displayTags(Event e){
        StringBuilder s = new StringBuilder();
        for (String tag: e.getTags()){
            s.append(tag);
            s.append(", ");
        }
        if (s.toString().equals("")){ return "";}
        return s.substring(0, s.length()-2);
    }

    private String displayMemoIds(Event e){
        StringBuilder s = new StringBuilder();
        for (int id: e.getMemoIDs()){
            s.append(id);
            s.append(", ");
        }
        if (s.toString().equals("")){ return "";}
        return s.substring(0, s.length()-2);
    }
    private String displayAlerts(Event e){
        StringBuilder s = new StringBuilder();
        for (Alert a: e.getAlerts()){
            s.append(a.pushReminder());
            s.append(", ");
        }
        if (s.toString().equals("")){ return "";}
        return s.substring(0, s.length()-2);
    }

    public String getEventsForSeries(User u){
        String[] s;
        int counter = 0;
        for (Event e: u.getEvents()){
            s = new String[]{e.getEventName(), e.getTime().getStart().toString(),
                    e.getTime().getEnd().toString(), e.getSeriesName(), displayTags(e),
            displayMemoIds(e), displayAlerts(e)};
            myModel.displayEventInfo(s, counter);
            counter ++;
        }
        myModel.displaySelectionEventsForSeries();
        Scanner kbReader = new Scanner(System.in);
        return kbReader.next();
    }
    public String getParamForSeries(){
        return "";
    }

}
