package com.group_0225;

public class UserManager {
    public User createUser(String username, String password, CalendarData calendarData){
        User temp = new User(username, password);
        calendarData.addUser(username, temp);
        return temp;
    }

    public boolean validatePassword(User user, String password){
        return user.validatePassword(password);
    }



}
