package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MemoController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel allows the user to create a new memo
 */
public class CreateMemoPanel extends CalendarLayoutPanel {
    /**
     * Creates a new CreateMemoPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
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

        addLabel(c, bottomPane, 0, viewModel.get("EnterEvent"));

        int i = 1;
        while (i < inputs.size()){
            JCheckBox checkBox = addCheckBox(inputs.get(i), bottomPane, i, c);
            events.add(checkBox);
            i++;
        }
        addLabel(c, bottomPane, i, viewModel.get("EnterMemo"));
        JTextArea memo = addTextArea(c, bottomPane, i + 1);
        Button create = addButton(c, bottomPane, i + 2, viewModel.get("CreateString"));

        if (inputs.get(0).equals("Error1")){
            addLabel(c, bottomPane, i + 3, viewModel.get("MemoError1"));
        }

        if (inputs.get(0).equals("Error2")){
            addLabel(c, bottomPane, i + 3, viewModel.get("MemoError2"));
        }

        if (inputs.get(0).equals("Created")){
            addLabel(c, bottomPane, i + 3, viewModel.get("MemoCreated"));
        }


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
