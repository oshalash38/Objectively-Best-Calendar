package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MemoController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This panel gives the user options with respect to their memos
 */
public class MemoOptionsPanel extends CalendarLayoutPanel {
    /**
     * Creates a new MemoOptionsPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public MemoOptionsPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        MemoController memoController = controllerContainer.getMemoController();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        JPanel bottomPane = new JPanel(new GridBagLayout());
        buildTitle(c, viewModel.get("eventOptions"));
        bottomPane.setPreferredSize(new Dimension(500, 500));

        Button editMemosCurrentEvent = addButton(c, bottomPane, 1, viewModel.get("editMemoCurrentEvent"));
        addLabel(c, bottomPane, 2, viewModel.get("description1"));
        Button editMemosAllEvents = addButton(c, bottomPane, 3, viewModel.get("editMemosAllEvents"));
        addLabel(c, bottomPane, 4, viewModel.get("description2"));

        editMemosCurrentEvent.addActionListener(e -> memoController.pushEditCurrentEvent(inputs));
        editMemosAllEvents.addActionListener(e -> memoController.pushEditAll(inputs));

        c.gridy = 2;
        this.add(bottomPane, c);

    }
}
