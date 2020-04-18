package com.group_0225.ui.core;

import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.common.util.UIPresenter;

import javax.swing.*;
import java.io.File;
import java.util.Map;

public class CalendarFrame extends JFrame{

    private UIPresenter presenter;
    private Map<String,CalendarLayoutPanel> panels;

    public CalendarFrame() {
        super("Objectively Best Calendar");

        //Logo from https://freeiconshop.com/icon/calendar-icon-glyph/
        File image = new File("phase2/src/com/group_0225/ui/core/Model/logo.png");
        ImageIcon imageIcon = new ImageIcon(image.getAbsolutePath());
        this.setIconImage(imageIcon.getImage());

        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
