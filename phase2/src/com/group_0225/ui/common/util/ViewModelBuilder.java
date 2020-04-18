package com.group_0225.ui.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all the Strings used by panels (especially CalendarLayoutPanel instances).
 */
public class ViewModelBuilder {
    /**
     * Instantiate a map of Strings to be used in panel display
     * @return a map of Strings
     */
    public Map<String, String> build() {
        Map<String, String> viewModel = new HashMap<>();
        viewModel.put("EventNameString", "Event Name:");
        viewModel.put("EnterStartDateString", "Enter Start Date:");
        viewModel.put("EnterEndDateString", "Enter End Date:");
        viewModel.put("CreateString", "Create");
        viewModel.put("DoneString", "Done");
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
        viewModel.put("EventError1", "Please enter all fields");
        viewModel.put("EventError2", "Please enter valid dates/times");
        viewModel.put("Start Date: ", "Start Date: ");
        viewModel.put("End Date: ", "End Date: ");
        viewModel.put("Start Time: ", "Start Time: ");
        viewModel.put("End Time: ", "End Time: ");
        viewModel.put("Memos:", "Memos:");
        viewModel.put("CreateSeriesFromScratch", "Create a series from scratch:");
        viewModel.put("CreateSeriesFromEvents", "Create a series from existing events:");
        viewModel.put("seriesname", "Choose series name:");
        viewModel.put("SeriesName:", "Series name: ");
        viewModel.put("freq", "How often will events in this series occur?");
        viewModel.put("CreateMemo", "Create New Memo");
        viewModel.put("EnterMemo", "Enter Memo: ");
        viewModel.put("Separator", "-------------------------------");
        viewModel.put("MemoError1", "Please select at least one event");
        viewModel.put("MemoError2", "Please enter a valid memo");
        viewModel.put("MemoCreated", "Memo Created.");
        viewModel.put("GET_EVENTS_BY_THRESHOLD", "Get Events By Date Threshold");
        viewModel.put("Get_events_by_name", "Get Events By Name");
        viewModel.put("EventName", "Event Name:");
        viewModel.put("Find", "Find");
        viewModel.put("NoMemos", "There are no memos to be displayed.");
        viewModel.put("MemoList", "Memo List");
        viewModel.put("EventList", "Event List");
        viewModel.put("From", "From: ");
        viewModel.put("memoOptions", "Memo Options");
        viewModel.put("editMemoCurrentEvent", "Edit memos only for this event");
        viewModel.put("description1", "(Changes will only happen to this event)");
        viewModel.put("editMemosAllEvents", "Edit memos for all associated events");
        viewModel.put("description2", "(Changes will happen for all associated events)");
        viewModel.put("editMemos", "Edit Memos:");

        viewModel.put("EnterStartTimeString", "Enter Start Time: (hh:mm:ss)");
        viewModel.put("EnterEndTimeString", "Enter End Time:");
        viewModel.put("EnterDurationSeriesString", "Enter the duration of each event in this series:");
        viewModel.put("NoEvents", "There are no events to be displayed.");
        viewModel.put("Days:", "Days:");
        viewModel.put("HMS:", "Hours/Minutes/Seconds:");
        viewModel.put("NE:", "Number of Events (min. 2):");
        viewModel.put("CREATESERIES", "CREATE SERIES");
        viewModel.put("Create series exclamation", "Create a series!");
        viewModel.put("SeriesDescription", "A series is a group of events sharing a theme (ex. birthdays).");
        viewModel.put("SeriesHow:", "How would you like to create your series?");
        viewModel.put("From scratch:", "From scratch");
        viewModel.put("From events:", "From events in this calendar");
        viewModel.put("SeriesEventsInstructions", "Select the events that you want to be in the new series.");
        viewModel.put("View Events by Series Name: ", "View Events by Series Name: ");
        viewModel.put("Which series would you like to view?", "Which series would you like to view?");
        viewModel.put("VIEW SERIES", "VIEW SERIES");
        viewModel.put("You have no series.", "You have no series.");
        viewModel.put("Choose name:","Choose name:");
        viewModel.put("View Events by Name", "View Events by Name");
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
        viewModel.put("Optional reply: ", "Optional reply: ");
        viewModel.put("View events by tag", "View events by tag");
        viewModel.put("Select a tag:", "Select a tag:");
        viewModel.put("VIEW EVENTS", "VIEW EVENTS");
        viewModel.put("No tags:", "You have no tags on any events.");
        viewModel.put("AddTags", "Optional: tags (put each tag on separate lines)");
        viewModel.put("Responses", "Responses: ");
        viewModel.put("OriginalRequest", "Original Request: ");
        viewModel.put("Response", "Response: ");
        viewModel.put("eventOptions", "Event Options");
        viewModel.put("Save", "Save");
        viewModel.put("Empty = Delete", "Delete a memo by leaving it empty.");
        viewModel.put("ModificationC", "Modifications Implemented Successfully");

        viewModel.put("CreateCalendarPanelTitle", "Create New Calendar");
        viewModel.put("CalendarNameString", "New Calendar Name:");
        viewModel.put("ConfirmString", "Confirm");

        viewModel.put("ChangeTimeTitleString", "Time Machine");
        viewModel.put("TimeTravelString", "Time travel!");
        viewModel.put("SelectDateString", "Select Date:");
        viewModel.put("SelectTimeString", "Select Time: (hh:mm:ss)");
        viewModel.put("SelectTimeFrequencyString", "Select Frequency:");
        viewModel.put("TimeChangeSuccessfulString", "Time travel successful. Local date and time: ");

        viewModel.put("EditString", "Edit");
        viewModel.put("DeleteString", "Delete");

        viewModel.put("CreateAlertTitleString", "Create Alert");
        viewModel.put("CreateOneTimeAlertTitleString", "Create One Time Alert");
        viewModel.put("CreateRepeatingAlertTitleString", "Create Repeating Alert");
        viewModel.put("RecurringAlertOrNot", "Is this a recurring alert or a one time alert?");
        viewModel.put("OneTimeButtonString", "One time alert");
        viewModel.put("RepeatingButtonString", "Repeating alert");
        viewModel.put("ViewAlertsButtonString", "View Alerts");
        viewModel.put("AlertString", "Alerts");
        viewModel.put("EditAlertButton", "Edit this alert");
        viewModel.put("DeleteAlertButton", "Delete this alert");
        viewModel.put("Notification", "You Hve New Notifications!");
        viewModel.put("notificationView", "View Notifications");

        viewModel.put("DaysFrequencyString", "Enter day frequency");
        viewModel.put("HourFrequencyString", "Enter hour frequency");
        viewModel.put("MinuteFrequencyString", "Enter minute frequency");

        viewModel.put("AlertNamePromptString", "Enter alert message:");
        viewModel.put("EditAlertString", "Edit Alert");
        viewModel.put("ChangeAlertDateAndTimeString", "Change Alert Date and Time");
        viewModel.put("ChangeAlertMessageString", "Change Alert Message");
        viewModel.put("ChangeAlertFrequencyString", "Change Alert Frequency");

        //TOOLBAR HEADERS
        viewModel.put("TOOLBARCreateMenuString", "Create");
        viewModel.put("TOOLBARViewMenuString", "View Events By");
        viewModel.put("TOOLBARMessagingFunctionsString", "Messaging Functions");
        viewModel.put("TOOLBARUserString", "User");
        viewModel.put("TOOLBARTimeMachineString", "Time Machine");
        viewModel.put("CurrentCalendarString", "Current calendar: ");
        viewModel.put("TOOLBARnotificationString", "Notifications");

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
        viewModel.put("TOOLBARMessagingFunctionsSendMessageString", "Send event");
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
