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
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("Password:", "Password:");
        temp.put("EnterStartTimeString", "Enter Start Time:");
        temp.put("EnterEndTimeString", "Enter End Time:");

        return temp;
    }
}
