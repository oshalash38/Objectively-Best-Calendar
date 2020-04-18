package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This Panel allows the user to select by which names events will be viewed
 */
public class ViewByENameChoicePanel extends CalendarLayoutPanel {
    /**
     * Creates a new ViewByENameChoicePanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public ViewByENameChoicePanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        EventController ec = controllerContainer.getEventsController();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel bottomPane = new JPanel(new GridBagLayout());

        buildTitle(constraints,viewModel.get("View Events by Name"));

        addLabel(constraints,bottomPane,2,viewModel.get("Choose name:"));
        TextField selection = addTextField(constraints,bottomPane,3,"","");
        Button view = addButton(constraints,bottomPane,5,viewModel.get("VIEW EVENTS"));
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ec.viewEventByNamePanel(selection.getText());
            }
        });
        addLabel(constraints,bottomPane,7,inputs.get(0));
        constraints.gridy = 1;
        this.add(bottomPane, constraints);
    }
}
