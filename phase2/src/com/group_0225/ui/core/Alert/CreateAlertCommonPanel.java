package com.group_0225.ui.core.Alert;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This panel allows the user to create an alert
 */
public class CreateAlertCommonPanel extends CalendarLayoutPanel {
    String titleKey;
    TextField alertName;
    JComboBox<String> eventChoice;

    /**
     * Creates a new CreateAlertCommonPanel instances
     * @param events list of events, as Strings
     * @param controllerContainer a ControllerContainer instance
     * @param title the title of the panel
     */
    public CreateAlertCommonPanel(List<String> events, ControllerContainer controllerContainer, String title){
        super(controllerContainer);
        this.titleKey = title;
        this.buildPanel(events);
    }

    /**
     * Builds this CalendarLayoutPanel
     * @param inputs the inputs to display
     */
    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        buildTitle(c, viewModel.get(titleKey));
        eventChoice = addDropDown(inputs.subList(1, inputs.size()),this,1,c);

        alertName = addTextField(c, this, 2, viewModel.get("AlertNamePromptString"),inputs.get(0));

    }

    public String getAlertNameUserInput(){
        return alertName.getText();
    }

    /**
     *
     * @return the string that the user has chosen. Could be null
     */
    public String getEventChoice()  throws InputMismatchException {
        return (String)eventChoice.getSelectedItem();
    }
}
