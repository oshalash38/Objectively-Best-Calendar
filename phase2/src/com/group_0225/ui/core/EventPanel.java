package com.group_0225.ui.core;

import com.group_0225.controller.*;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventPanel extends CalendarLayoutPanel {
    public EventPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        EventController eventController = controllerContainer.getEventsController();
        CalendarGridController gridController = controllerContainer.getCalendarGridController();
        AlertController alertController = controllerContainer.getAlertController();
        MemoController memoController = controllerContainer.getMemoController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, inputs.get(1));
        JPanel optionsHolder = new JPanel(new GridBagLayout());
        Button editButton = this.addButton(c, optionsHolder, 1, "Edit");
        editButton.addActionListener(e -> eventController.pushEditEvent(inputs.get(0)));
        Button deleteButton = this.addButton(c, optionsHolder, 1, "Delete");

        Button viewAlerts = this.addButton(c, optionsHolder, 2, viewModel.get("ViewAlertsButtonString"));
        Button memoOptions = addButton(c, optionsHolder, 2, viewModel.get("memoOptions"));

        memoOptions.addActionListener(e -> memoController.pushMemoOptions(inputs.get(0)));

        deleteButton.addActionListener(e -> eventController.deleteEvent(inputs.get(0), gridController));
        viewAlerts.addActionListener(e-> alertController.pushViewAlertsPanel(inputs.get(0)));

        this.add(optionsHolder, c);

        JPanel bottomPane = new JPanel(new GridBagLayout());

        addLabel(c, bottomPane, 2, viewModel.get("Start Date: ") + inputs.get(2));
        addLabel(c, bottomPane, 4, viewModel.get("End Date: ") + inputs.get(3));
        addLabel(c, bottomPane, 6, viewModel.get("Start Time: ") + inputs.get(4));
        addLabel(c, bottomPane, 8, viewModel.get("End Time: ") + inputs.get(5));
        addLabel(c, bottomPane, 10, viewModel.get("SeriesName:") + inputs.get(6));
        addLabel(c, bottomPane, 12, viewModel.get("Memos:"));
        List<String> memos = inputs.subList(7, inputs.size());
        int i = 0;
        int j = 0;
        while (i < memos.size()){
            JTextArea memo = addTextArea(memos.get(i), bottomPane, 13 + j, c);
            memo.setEditable(false);
            addLabel(c, bottomPane, 14 + j, viewModel.get("Separator"));
            i++;
            j = j + 2;
        }

        for(String s : inputs)
            System.err.println(s);



        c.gridy = 2;
        this.add(bottomPane, c);
    }
}
