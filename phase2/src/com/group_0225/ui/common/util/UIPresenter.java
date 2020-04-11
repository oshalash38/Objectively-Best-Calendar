package com.group_0225.ui.common.util;

import java.util.Observable;

public class UIPresenter extends Observable {

    public void updateUI(UIUpdateInfo info) {
        setChanged();
        notifyObservers(info);
    }
    public void startUp(){
        setChanged();
        notifyObservers(new UIUpdateInfo("StartupPanel", null));
    }
}
