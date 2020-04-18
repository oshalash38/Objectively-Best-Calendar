package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.UsersCalendarController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import com.group_0225.ui.common.util.ViewModelBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * This panel allows the user to create a new calendar
 */
public class CreateCalendarPanel extends CalendarLayoutPanel {
    /**
     * Creates a new CreateCalendarPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CreateCalendarPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        UsersCalendarController usersCalendarController = controllerContainer.getUsersCalendarController();
        Map<String, String> viewModel = new ViewModelBuilder().build();


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("CreateCalendarPanelTitle"));

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField calendarName = addTextField(c, bottomPane, 1, viewModel.get("CalendarNameString"), inputs.get(0));
        Button confirmButton = addButton(c, bottomPane, 2, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e -> usersCalendarController.addCalendar(calendarName.getText()));

        c.gridy = 6;
        this.add(new Label(inputs.get(1)), c);

        //Adds bottom pane
        c.gridy = 1;
        this.add(bottomPane, c);

    }
}
