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
        views.put(UIViews.alertView, new AlertView());
        views.put(UIViews.createRecurringAlertView, new CreateRecurringAlertView());
        views.put(UIViews.createOneAlertView, new CreateOneAlertView());
        views.put(UIViews.createUser, new CreateNewUserView());
        views.put(UIViews.eventInfo, new EventView());
        views.put(UIViews.startup, new MenuView("CALENDAR V1\n===============" +
                        "\n1. Login\n2. Create new user\n3. Exit", 3));
        views.put(UIViews.mainMenu, new MenuView(
                 "Main Menu:\n1. Check upcoming alerts\n2. Create memo\n3. Create event" +
                         "\n4. Create series\n5. Display events filtered by..." +
                         "\n6. Logout", 6));
        views.put(UIViews.displayEventBy, new MenuView(
                "Display events filtered by...\n1. Current events\n2. Past events\n3. Future events" +
                        "\n4. Date threshold\n5. Memo\n6. Tag\n7. Name\n8. Series\n9. Return", 9));
        views.put(UIViews.eventType, new MenuView("What event type would you like to create?" +
                        "\n1. Regular event\n2. Series of events\n3.Return", 3));
        views.put(UIViews.userDNE, new MenuView("User Does Not Exist\n1. Try again" +
                "\n2. Return to startup page", 2));
        views.put(UIViews.eventOptions, new MenuView("What event type would you like to create?\n" +
                "1. Regular event\n2. Series of events\n3, Return to main menu", 3));

        views.put(UIViews.EventManipulation, new MenuView("1. Change event name\n2. Create one time alert\n3. Create recurring alert\n4. Associate with memo\5. Return", 5));

        views.put(UIViews.createDateTimeView, new CreateDateTimeView());
        views.put(UIViews.createSeriesScratch, new CreateSeriesScratchView());
        views.put(UIViews.createSeriesEvents, new CreateSeriesEventsView());

        views.put(UIViews.chooseFrequency, new MenuView("What is the frequency of events" +
                "in this series?\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly", 5));
        views.put(UIViews.doesUserWantToEdit, new MenuView("Would you like to edit one of these events?\n1. Yes\n2. No", 2));

        views.put(UIViews.loginView, new LoginView());
        views.put(UIViews.createEvent, new CreateNewEventView());
        views.put(UIViews.createMemo, new CreateMemoView());
        views.put(UIViews.listEvents, new ListEventsView());
        views.put(UIViews.editMemo, new EditMemoView());
        views.put(UIViews.seriesMenu, new MenuView("Would you like to create a series: \n 1: By choosing events to make into a new series \n 2: By making a completely" +
                "new series from scratch. \n Type your selection (1 or 2).", 2));
        views.put(UIViews.ChangeName, new ChangeName());
        views.put(UIViews.memoEventPicking, new MemoEventPickingView());
    }
}
