package com.group_0225.ui.common.util;

import java.util.List;

/**
 * This class is a package of information that allows the controllers to direct GUI logic concisely (referring to panel creation and deletion)
 */
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
     *                  "scrollable" if the update is intended to push a scrollable dialog
     *                  "toolbar" if update is intended for the toolbar.
     * @param data the data for the front end element
     * @param panelKey the key for the panel if the update is intended for a new p
     */
    public UIUpdateInfo(String recipient, List<String> data, String panelKey){
        this.recipient = recipient;
        this.data = data;
        this.panelKey = panelKey;
    }

    /**
     * Getter method for the panel key
     * @return the panel key
     */
    public String getPanelKey() {
        return panelKey;
    }

    /**
     * Getter method for the list of data
     * @return the data
     */
    public List<String> getData() {
        return data;
    }

    /**
     * Getter method for the type of panel to be created
     * @return the recipient, as a String
     */
    public String getRecipient() {
        return recipient;
    }
}
