package com.group_0225.ui.common.util;

import java.util.HashMap;
import java.util.Map;

public class ViewModelBuilder {
    public Map<String, String> build(){
        Map<String, String> viewModel = new HashMap<>();
        viewModel.put("EventNameString", "Event Name:");
        viewModel.put("EnterStartDateString", "Enter Start Date:");
        viewModel.put("EnterEndDateString", "Enter End Date:");
        viewModel.put("CreateString", "Create");
        viewModel.put("CancelString", "Cancel");
        viewModel.put("return", "return");
        viewModel.put("CreateEventString", "Create Event");
        viewModel.put("Create New User", "Create New User");
        viewModel.put("Repeat Password:", "Repeat Password:");
        viewModel.put("OBJECTIVELY BEST CALENDAR", "OBJECTIVELY BEST CALENDAR");
        viewModel.put("Password:", "Password:");
        viewModel.put("Username:", "Username:");
        viewModel.put("Login", "Login");
        viewModel.put("EventCreated", "Event Created.");
        viewModel.put("Start Date: ", "Start Date: ");
        viewModel.put("End Date: ", "End Date: ");
        viewModel.put("Start Time: ", "Start Time: ");
        viewModel.put("End Time: ", "End Time: ");
        viewModel.put("Memos:", "Memos:");
        viewModel.put("CreateSeriesFromScratch", "Create a series from scratch:");
        viewModel.put("CreateSeriesFromEvents", "Create a series from existing events:");
        viewModel.put("seriesname", "Choose series name:");
        viewModel.put("SeriesName:", "Series name: ");
        //viewModel.put("CreateSeriesFromEvents", "Create a series from events");
        viewModel.put("freq", "How often will events in this series occur?");
        viewModel.put("CreateMemo", "Create New Memo");
        viewModel.put("EnterMemo", "Enter Memo: ");

        viewModel.put("EnterStartTimeString", "Enter Start Time:");
        viewModel.put("EnterEndTimeString", "Enter End Time:");
        viewModel.put("EnterDurationSeriesString", "Enter the duration of each event in this series:");
        viewModel.put("NoEvents", "There are no events to be displayed.");
        viewModel.put("Days:", "Days:");
        viewModel.put("HMS:", "Hours/Minutes/Seconds:");
        viewModel.put("NE:", "Number of Events (min. 2):");
        viewModel.put("CREATESERIES", "CREATE SERIES");
        viewModel.put("Create series exclamation", "Create a series!");
        viewModel.put("SeriesDescription","A series is a group of events sharing a theme (ex. birthdays).");
        viewModel.put("SeriesHow:", "How would you like to create your series?");
        viewModel.put("From scratch:","From scratch");
        viewModel.put("From events:", "From events in this calendar");
        viewModel.put("SeriesEventsInstructions", "Select the events that you want to be in the new series.");
        viewModel.put("SendMessage", "Send Message To User");
        viewModel.put("EnterUsername", "Enter Username: ");
        viewModel.put("EnterEvent", "Pick Event: ");
        viewModel.put("EnterMessage", "Enter Message: ");
        viewModel.put("Send", "Send!");
        viewModel.put("Inbox", "Inbox");
        viewModel.put("Requests", "Requests: ");
        viewModel.put("Event information: ", "Event information: ");
        viewModel.put("Accept invitation: ", "Accept invitation: ");
        viewModel.put("Decline invitation: ", "Decline invitation: ");
        viewModel.put("Optional reply: ","Optional reply: ");

        viewModel.put("CreateCalendarPanelTitle", "Create New Calendar");
        viewModel.put("CalendarNameString", "New Calendar Name:");
        viewModel.put("ConfirmString", "Confirm");

        viewModel.put("ChangeTimeTitleString", "Time Machine");
        viewModel.put("TimeTravelString", "Time travel!");
        viewModel.put("SelectDateString", "Select Date:");
        viewModel.put("SelectTimeString", "Select Time:");
        viewModel.put("TimeChangeSuccessfulString", "Time travel successful. Local date and time: ");

        viewModel.put("CreateAlertTitleString", "Create Alert");
        viewModel.put("RecurringAlertOrNot", "Is this a recurring alert or a one time alert?");
        viewModel.put("OneTimeButtonString", "One time alert");
        viewModel.put("RepeatingButtonString", "Repeating alert");


        //TOOLBAR HEADERS
        viewModel.put("TOOLBARCreateMenuString", "Create");
        viewModel.put("TOOLBARViewMenuString", "View Events By");
        viewModel.put("TOOLBARMessagingFunctionsString", "Messaging Functions");
        viewModel.put("TOOLBARUserString", "User");
        viewModel.put("TOOLBARTimeMachineString", "Time Machine");

        //TOOLBAR options for Create choice
        viewModel.put("TOOLBARCreateMenuEventString", "Create Event");
        viewModel.put("TOOLBARCreateMenuAlertString", "Create Alert");
        viewModel.put("TOOLBARCreateMenuMemoString", "Create Memo");
        viewModel.put("TOOLBARCreateMenuSeriesString", "Create Series");

        //TOOLBAR options for View choice
        viewModel.put("TOOLBARViewMenuCurrentEventsString", "Current events");
        viewModel.put("TOOLBARViewMenuPastEventsString", "Past events");
        viewModel.put("TOOLBARViewMenuFutureEventsString", "Future events");
        viewModel.put("TOOLBARViewMenuDateThresholdString", "Date threshold");
        viewModel.put("TOOLBARViewMenuMemoString", "Memo");
        viewModel.put("TOOLBARViewMenuTagString", "Tag");
        viewModel.put("TOOLBARViewMenuNameString", "Name");
        viewModel.put("TOOLBARViewMenuSeriesString", "Series");

        //TOOLBAR options for Messaging choice
        viewModel.put("TOOLBARMessagingFunctionsSendMessageString", "Send new message");
        viewModel.put("TOOLBARMessagingFunctionsInboxString", "Inbox");

        //TOOLBAR options for User choice
        viewModel.put("TOOLBARUserChangeCalendarString", "Change Calendar");
        viewModel.put("TOOLBARUserAddNewCalendarString", "Add New Calendar");
        viewModel.put("TOOLBARUserLogoutString", "Logout");

        //TOOLBAR options for time machine choice
        viewModel.put("TOOLBARTimeMachineReturnToPresentString", "Back to the present");
        viewModel.put("TOOLBARTimeMachineTimeTravelString", "Activate time travel");


        return viewModel;
    }
}
