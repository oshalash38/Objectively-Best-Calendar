package com.group_0225.ui.common.util;

import java.util.Observable;

/**
 * This class is part of our implementation of the Observer design pattern. It notifies the UIManager of required changes to the UI,
 * and gives it the tools to implement the changes.
 */
public class UIPresenter extends Observable {
    /**
     * Notifies the UIManager of required changes to the UI, requested by the User
     * @param info a UIUpdateInfo instance
     */
    public void updateUI(UIUpdateInfo info) {
        setChanged();
        notifyObservers(info);
    }
}
