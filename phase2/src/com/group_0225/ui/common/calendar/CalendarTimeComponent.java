package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * This class extends CalendarLayoutPanel to accomplish time-related functionality
 */
public class CalendarTimeComponent extends CalendarLayoutPanel {
    /**
     * Creates a new CalendarTimeComponent instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarTimeComponent(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        EventController eventsController = controllerContainer.getEventsController();

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

        if(inputs.get(3).equals("true")) {
            dayHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(dayHolder, c);

        MouseListener viewEventListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventsController.viewEvents(inputs.get(0), inputs.get(1), inputs.get(2));
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
        };

        if (!inputs.get(4).equals("0")) {
            c.anchor = GridBagConstraints.CENTER;
            Label eventNumber = new Label("Events: " + inputs.get(4));
            this.add(eventNumber, c);
            eventNumber.addMouseListener(viewEventListener);
        }

        c.anchor = GridBagConstraints.FIRST_LINE_END;
        JPanel weatherHolder = new JPanel();
        if(!inputs.get(5).equals("NONE")) {
            JLabel weatherLabel = new JLabel("");
            weatherLabel.setText(inputs.get(5) + "°C");
            weatherLabel.addMouseListener(viewEventListener);
            weatherHolder.add(weatherLabel);
        }
        this.add(weatherHolder, c);

        this.addMouseListener(viewEventListener);
        dayHolder.addMouseListener(viewEventListener);
        dayNumber.addMouseListener(viewEventListener);
    }


}
