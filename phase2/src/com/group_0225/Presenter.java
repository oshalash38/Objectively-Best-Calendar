package com.group_0225;

import com.group_0225.views.*;
import com.group_0225.ui.core.CalendarFrame;

import java.util.*;



/**
 * This class controls the information that is pushed to the user.
 *
 * @author Daniel Shoichet
 */
public class Presenter {
    public Map<UIViews, CalendarView> views;
    public CalendarFrame ui;

    /**Creates a presenter instance and initializes a hashmap of views.
     *
     */
    public Presenter(){
        ui = new CalendarFrame();
        ui.setVisible(true);
        views = new HashMap<>();
        generateHashMap();
    }

    /**
     * Handles interactions between controller and the UI
     * @param view the name of the screen that needs to pe presented
     * @param info the additional info that may need to be displayed.
     * @return the input that the user may have entered.
     */
    public List<String> displayView(UIViews view, List<String> info) {
        CalendarView current = views.get(view);
        return current.activateView(info);
    }

    private void generateHashMap(){
        views.put(UIViews.alertView, new AlertView());
        views.put(UIViews.createRecurringAlertView, new CreateRecurringAlertView());
        views.put(UIViews.createOneAlertView, new CreateOneAlertView());
        views.put(UIViews.createUser, new CreateNewUserView());
        views.put(UIViews.eventsInfoWithNumbers, new EventsView());
        views.put(UIViews.startup, new MenuView("CALENDAR V1\n===============" +
                "\n1. Login\n2. Create new user\n3. Exit", 3));
        views.put(UIViews.mainMenu, new MenuView(
                "Main Menu:\n1. Check notifications\n2. Check upcoming alerts\n3. Create memo\n4. Create event" +
                        "\n5. Create series\n6. Display events filtered by..." +
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

        views.put(UIViews.EventManipulation, new MenuView("1. Change event name\n2. Create one time alert"+
                "\n3. Create recurring alert\n4. Associate with memo\n5. Associate with tag " +
                "\n6. Return", 6));
        views.put(UIViews.dateThreshold, new DateThresholdView());

        views.put(UIViews.createDateTimeView, new CreateDateTimeView());
        views.put(UIViews.createSeriesScratch, new CreateSeriesScratchView());
        views.put(UIViews.createSeriesEvents, new CreateSeriesEventsView());

        views.put(UIViews.chooseFrequency, new MenuView("What is the frequency of events" +
                "in this series?\n 1: Hourly \n 2: Daily \n 3: Weekly \n 4: Monthly \n 5: Yearly", 5));
        views.put(UIViews.doesUserWantToEdit, new MenuView("Would you like to edit one of these events?" +
                "\n1. Yes\n2. No", 2));

        views.put(UIViews.loginView, new LoginView());
        views.put(UIViews.createEvent, new CreateNewEventView());
        views.put(UIViews.createMemo, new CreateMemoView());
        views.put(UIViews.editMemo, new EditMemoView());
        views.put(UIViews.seriesMenu, new MenuView("Would you like to: " +
                "\n 1: Create a series by choosing existing events to make into a new series \n 2: Create a series by making new events" +
                " from scratch. \n3: Return to the main menu. \nType your selection (1, 2 or 3).", 3));
        views.put(UIViews.ChangeName, new ChangeName());
        views.put(UIViews.memoEventPicking, new MemoEventPickingView());
        views.put(UIViews.SearchEvents, new SearchEvents());
        views.put(UIViews.CreateTag, new CreateTag());
        views.put(UIViews.memoMenu, new MemoMenuView());
        views.put(UIViews.EventInfo , new EventInfo());
        views.put(UIViews.eventBySeriesName, new EventBySeriesName());
        views.put(UIViews.eventsView, new EventsView());
    }
}
