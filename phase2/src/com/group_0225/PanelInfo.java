package com.group_0225;

import java.util.List;

public class PanelInfo {

    String panelKey;
    List<String> panelData;
    boolean isDialog;

    public PanelInfo(String panelKey, List<String> panelData){
        this.panelKey = panelKey;
        this.panelData = panelData;
        this.isDialog = false;
    }

    public PanelInfo(String panelKey, List<String> panelData, boolean isDialog){
        this.panelKey = panelKey;
        this.panelData = panelData;
        this.isDialog = isDialog;
    }

    public String getPanelKey() {
        return panelKey;
    }

    public List<String> getPanelData() {
        return panelData;
    }

    public boolean isDialog() { return isDialog; }
}
