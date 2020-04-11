package com.group_0225.ui.common.util;

import java.util.List;

public class UIUpdateInfo {

    String panelKey;
    List<String> data;
    String recipient;

    /**
     *
     * @param recipient "panel" if update is intended for a new panel in CalendarFrame.
     *                  "dialog" if the update is intended to push a new dialog.
     *                  "toolbar" if update is intended for the toolbar.
     * @param data the data for the front end element
     */
    public UIUpdateInfo(String recipient, List<String> data){
        this.recipient = recipient;
        this.data = data;
        this.panelKey = "";
    }

    /**
     *
     * @param recipient "panel" if update is intended for a new panel in CalendarFrame.
     *                  "dialog" if the update is intended to push a new dialog.
     *                  "toolbar" if update is intended for the toolbar.
     * @param data the data for the front end element
     * @param panelKey the key for the panel if the update is intended for a new p
     */
    public UIUpdateInfo(String recipient, List<String> data, String panelKey){
        this.recipient = recipient;
        this.data = data;
        this.panelKey = panelKey;
    }


    public String getPanelKey() {
        return panelKey;
    }

    public List<String> getData() {
        return data;
    }

    public String getRecipient() {
        return recipient;
    }
}
