package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MemoController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel allows the user to view all their memos
 */
public class MemoListPanel extends CalendarLayoutPanel {
    public MemoListPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        MemoController memoController = controllerContainer.getMemoController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;
        // Source: https://stackoverflow.com/questions/39348314/set-a-title-for-a-jpanel-on-eclipse
        String title = viewModel.get("MemoList");
        Border border = BorderFactory.createTitledBorder(title);

        this.setBorder(border);
        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.darkGray);

        if (inputs.size() == 0){
            JLabel label = new JLabel(viewModel.get("NoMemos"));
            parent.add(label);
        }
        List<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            c.gridy = i;

            String eventRawID = inputs.get(i);

            JButton memoPreview = new JButton(memoController.getMemo(eventRawID));
            memoPreview.addActionListener(e -> memoController.displayEventsAssociatedWithMemo(eventRawID));

            parent.add(memoPreview, c);
            buttons.add(memoPreview);
        }

        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);

        this.add(panel, c);
    }
}
