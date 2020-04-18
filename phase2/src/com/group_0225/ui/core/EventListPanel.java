package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows users to view Events as a list. The events included depend on criteria decided in the controllers.
 */
public class EventListPanel extends CalendarLayoutPanel {
    /**
     * Creates a new EventListPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public EventListPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController eventController = controllerContainer.getEventsController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;
        // Source: https://stackoverflow.com/questions/39348314/set-a-title-for-a-jpanel-on-eclipse
        String title = viewModel.get("EventList");
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);


        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.darkGray);

        if (inputs.size() == 0){
            JLabel label = new JLabel(viewModel.get("NoEvents"));
            parent.add(label);
        }
        List<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            c.gridy = i;

            String eventRawID = inputs.get(i);

            JButton eventPreview = new JButton(eventController.getEventName(eventRawID));
            eventPreview.addActionListener(e -> eventController.displayEvent(eventRawID));

            parent.add(eventPreview, c);
            buttons.add(eventPreview);
        }

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);

        this.add(panel, c);
    }
}
