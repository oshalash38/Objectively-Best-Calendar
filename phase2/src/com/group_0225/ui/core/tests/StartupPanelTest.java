package com.group_0225.ui.core.tests;

import com.group_0225.UIPresenter;
import com.group_0225.ui.core.CalendarFrame;

public class StartupPanelTest {

    public static void main(String[] args) {
        CalendarFrame frame = new CalendarFrame();
        UIPresenter presenter = new UIPresenter();
        presenter.addObserver(frame);
        frame.run(presenter);
    }
}
