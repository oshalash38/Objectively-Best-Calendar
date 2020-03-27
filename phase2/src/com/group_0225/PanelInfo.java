package com.group_0225;

import java.util.List;

public class PanelInfo {

    String panelKey;
    List<String> panelData;

    public PanelInfo(String panelKey, List<String> panelData){
        this.panelKey = panelKey;
        this.panelData = panelData;
    }

    public String getPanelKey() {
        return panelKey;
    }

    public List<String> getPanelData() {
        return panelData;
    }
}
