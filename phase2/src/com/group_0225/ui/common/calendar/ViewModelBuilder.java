package com.group_0225.ui.common.calendar;

import java.util.HashMap;
import java.util.Map;

public class ViewModelBuilder {
    public Map<String, String> build(){
        Map<String, String> temp = new HashMap<>();
        temp.put("EventNameString", "Event Name:");
        temp.put("EnterStartDateString", "Enter Start Date:");
        temp.put("EnterEndDateString", "Enter End Date:");
        temp.put("CreateString", "Create");
        temp.put("CancelString", "Cancel");
        temp.put("return", "return");
        temp.put("CreateEventString", "Create Event");
        temp.put("Create New User", "Create New User");
        temp.put("Repeat Password:", "Repeat Password:");
        temp.put("OBJECTIVELY BEST CALENDAR", "OBJECTIVELY BEST CALENDAR");
        temp.put("Password:", "Password:");
        temp.put("Username:", "Username:");
        temp.put("Login", "Login");
        temp.put("EventCreated", "Event Created.");

        temp.put("EnterStartTimeString", "Enter Start Time:");
        temp.put("EnterEndTimeString", "Enter End Time:");
        temp.put("NoEvents", "There are no events to be displayed.");



        //TOOLBAR HEADERS
        temp.put("TOOLBARCreateMenuString", "Create");
        temp.put("TOOLBARViewMenuString", "View Events By");
        temp.put("TOOLBARMessagingFunctionsString", "Messaging Functions");
        temp.put("TOOLBARUserString", "User");
        temp.put("TOOLBARTimeMachineString", "Time Machine");

        //TOOLBAR options for Create choice
        temp.put("TOOLBARCreateMenuEventString", "Create Event");
        temp.put("TOOLBARCreateMenuAlertString", "Create Alert");
        temp.put("TOOLBARCreateMenuMemoString", "Create Memo");
        temp.put("TOOLBARCreateMenuSeriesString", "Create Series");

        //TOOLBAR options for View choice
        temp.put("TOOLBARViewMenuCurrentEventsString", "Current events");
        temp.put("TOOLBARViewMenuPastEventsString", "Past events");
        temp.put("TOOLBARViewMenuFutureEventsString", "Future events");
        temp.put("TOOLBARViewMenuDateThresholdString", "Date threshold");
        temp.put("TOOLBARViewMenuMemoString", "Memo");
        temp.put("TOOLBARViewMenuTagString", "Tag");
        temp.put("TOOLBARViewMenuNameString", "Name");
        temp.put("TOOLBARViewMenuSeriesString", "Series");

        //TOOLBAR options for Messaging choice
        temp.put("TOOLBARMessagingFunctionsSendMessageString", "Send new message");
        temp.put("TOOLBARMessagingFunctionsInboxString", "Inbox");

        //TOOLBAR options for User choice
        temp.put("TOOLBARUserChangeCalendarString", "Change Calendar");
        temp.put("TOOLBARUserLogoutString", "Logout");

        //TOOLBAR options for time machine choice
        temp.put("TOOLBARTimeMachineReturnToPresentString", "Back to the present");
        temp.put("TOOLBARTimeMachineTimeTravelString", "Activate time travel");


        return temp;
    }
}
