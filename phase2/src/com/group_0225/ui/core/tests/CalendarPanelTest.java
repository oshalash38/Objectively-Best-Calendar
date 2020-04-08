package com.group_0225.ui.core.tests;

import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.manager.Init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalendarPanelTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Init myCalendar = new Init();
        myCalendar.run();

        List<String> inputs = new ArrayList<>();
        inputs.add("Poggers");
        inputs.add("4");
        inputs.add("2020");
        Init.p.displayPanel(new PanelInfo("CalendarPanel", inputs));
    }
}
