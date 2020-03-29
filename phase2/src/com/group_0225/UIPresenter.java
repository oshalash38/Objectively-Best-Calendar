package com.group_0225;

import com.group_0225.ui.core.CalendarFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class UIPresenter extends Observable {

    public void createUserPanel(){
        System.out.println("Made it ");
        setChanged();
        notifyObservers(new PanelInfo("CreateUserPanel", Arrays.asList("", "")));
    }

    public void createUserPanel(String triedUsername, String message){
        setChanged();
        notifyObservers(new PanelInfo("CreateUserPanel", Arrays.asList(triedUsername, message)));
    }

    public void startUp(){
        setChanged();
        notifyObservers(new PanelInfo("StartupPanel", null));
    }

    
}
