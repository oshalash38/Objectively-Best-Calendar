package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerFacade;
import com.group_0225.ui.core.EventListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class CalendarTimeComponent extends CalendarLayoutPanel {

    public CalendarTimeComponent(ControllerFacade controllerFacade) {
        super(new GridBagLayout(), controllerFacade);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;


        JPanel dayHolder = new JPanel();
        Label dayNumber = new Label(inputs.get(0));
        dayHolder.add(dayNumber, c);

        if(inputs.get(1).equals("true")) {
            dayHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(dayHolder, c);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controllerFacade.viewEvents();
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
