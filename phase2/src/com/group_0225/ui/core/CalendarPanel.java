package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarComponent;
import com.group_0225.ui.common.calendar.CalendarFooter;
import com.group_0225.ui.common.calendar.CalendarHeader;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import java.awt.*;
import java.util.List;

/**
 * This panel represents the basic calendar
 */
public class CalendarPanel extends CalendarLayoutPanel {

    CalendarHeader header;
    CalendarComponent component;
    CalendarFooter footer;

    /**
     * Creates a CalendarPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        int currMonth = Integer.parseInt(inputs.get(2));
        int currYear = Integer.parseInt(inputs.get(3));

        if (header != null)
            this.remove(header);
        header = new CalendarHeader(currMonth, currYear);

        if (component != null)
            this.remove(component);
        component = new CalendarComponent(controllerContainer);
        component.updatePanel(inputs);

        if (footer != null)
            this.remove(footer);
        footer = new CalendarFooter(new GridBagLayout(), controllerContainer);
        footer.updatePanel(inputs);

        displayCalendar();
    }

    private void displayCalendar() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.weightx = 1;

        this.add(header, c);
        header.setVisible(true);
        c.gridy = 1;
        c.weighty = 5;
        this.add(component, c);
        c.gridy = 2;
        c.weighty = 1;
        this.add(footer, c);

        this.revalidate();
    }


}
