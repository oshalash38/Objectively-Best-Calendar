package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MemoController;
import com.group_0225.entities.Event;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateMemoPanel extends CalendarLayoutPanel {

    public CreateMemoPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        MemoController memoController = controllerContainer.getMemoController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("CreateMemo"));

        JPanel bottomPane = new JPanel(new GridBagLayout());

        List<JCheckBox> events = new ArrayList<>();

        addLabel(c, bottomPane, 1, viewModel.get("EnterEvent"));

        int i = 0;
        while (i < inputs.size()){
            JCheckBox checkBox = addCheckBox(inputs.get(i), bottomPane, 2 + i, c);
            events.add(checkBox);
            i++;
        }
        addLabel(c, bottomPane, 2 + i, viewModel.get("EnterMemo"));
        JTextArea memo = addTextArea(c, bottomPane, i+3);
        Button create = addButton(c, bottomPane, i+4, viewModel.get("CreateString"));


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> eventStrings = new ArrayList<>();
                for (JCheckBox checkBox : events){
                    if (checkBox.isSelected()){
                        eventStrings.add(checkBox.getText());
                    }
                }
                memoController.createMemo(memo.getText(), eventStrings);
            }
        });

        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
