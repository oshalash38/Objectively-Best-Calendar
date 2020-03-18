package com.group_0225.ui.common.calendar;

import com.group_0225.ui.core.TestDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalendarTimeComponent extends JPanel {

    //TODO There has got to be a better way to do this
    JPanel itself;

    public CalendarTimeComponent(int day) {
        super(new GridBagLayout());

        itself = this;

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        Label dayNumber = new Label(day + "");
        this.add(dayNumber, c);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TestDialog testdialog = new TestDialog((JFrame) SwingUtilities.getWindowAncestor(itself), "Test");
                testdialog.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}
