package com.group_0225.ui.core;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.EventController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateEventPanel extends CalendarLayoutPanel {

    public CreateEventPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {


        EventController eventController = controllerContainer.getEventsController();
        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        buildTitle(c, viewModel.get("CreateEventString"));

        JPanel bottomPane = new JPanel(new GridBagLayout());
        TextField nameField = addTextField(c,bottomPane,1, viewModel.get("EventNameString"),"");
        addLabel(c, bottomPane, 2, viewModel.get("EnterStartDateString"));
        JDatePickerImpl startDateField = addCalendarPicker(c, bottomPane, 3);
        addLabel(c, bottomPane, 4, viewModel.get("EnterStartTimeString"));
        JSpinner startTimeSpinner = addTimeSpinner(c, bottomPane, 5);
        addLabel(c, bottomPane, 6, viewModel.get("EnterEndDateString"));
        JDatePickerImpl endDateField = addCalendarPicker(c, bottomPane, 7);
        addLabel(c, bottomPane, 8, viewModel.get("EnterEndTimeString"));
        JSpinner endTimeSpinner = addTimeSpinner(c, bottomPane, 9);
        Button create = addButton(c, bottomPane, 10, viewModel.get("DoneString"));

        if(inputs != null && inputs.contains("Edit")) {
            int indexOfEdit = inputs.indexOf("Edit");

            nameField.setText(inputs.get(indexOfEdit + 1));
            DateModel start = startDateField.getModel();
            start.setDate(Integer.parseInt(inputs.get(indexOfEdit + 2)), Integer.parseInt(inputs.get(indexOfEdit + 3)), Integer.parseInt(inputs.get(indexOfEdit + 4)));
            start.setSelected(true);
            startTimeSpinner.setValue(new Date(0,1,1, Integer.parseInt(inputs.get(indexOfEdit + 5)), Integer.parseInt(inputs.get(indexOfEdit + 6))));

            DateModel end = endDateField.getModel();
            end.setDate(Integer.parseInt(inputs.get(indexOfEdit + 7)), Integer.parseInt(inputs.get(indexOfEdit + 8)), Integer.parseInt(inputs.get(indexOfEdit + 9)));
            end.setSelected(true);
            endTimeSpinner.setValue(new Date(0,1,1, Integer.parseInt(inputs.get(indexOfEdit + 10)), Integer.parseInt(inputs.get(indexOfEdit + 11))));
        }

        if (inputs != null && inputs.size() > 0) {
            if (inputs.get(0).equals("Error1")){
                addLabel(c, bottomPane, 12, viewModel.get("EventError1"));
            } else if (inputs.get(0).equals("Created")){
                addLabel(c, bottomPane, 12, viewModel.get("EventCreated"));
            } else if (inputs.get(0).equals("Error2")){
                addLabel(c, bottomPane, 12, viewModel.get("EventError2"));
            } else if (inputs.get(0).equals("Edited")) {
                addLabel(c, bottomPane, 12, "Event Edited");
            }
        }
//        Button goBack = addButton(c, bottomPane, 11, viewModel.get("CancelString"));



//        goBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                controllerFacade.mainMenu();
//            }
//        });

        create.addActionListener(e -> {
            System.out.println(startDateField.getJFormattedTextField().getText());
            System.out.println(endDateField.getJFormattedTextField().getText());
            System.out.println(startTimeSpinner.getValue().toString().substring(11,19));
            System.out.println(endTimeSpinner.getValue().toString().substring(11,19));
            List<String> inputs1 = new ArrayList<>();
            try{
                inputs1.add(nameField.getText());
                inputs1.add(startDateField.getJFormattedTextField().getText());
                inputs1.add(endDateField.getJFormattedTextField().getText());
                inputs1.add(startTimeSpinner.getValue().toString().substring(11,19));
                inputs1.add(endTimeSpinner.getValue().toString().substring(11,19));
                if (inputs1.get(0).equals("")){
                    List<String> errList = new ArrayList<>();
                    errList.add("Error1");

                    eventController.createEvent(errList);
                }
                else if(inputs != null && inputs.contains("Edit")) {
                    int indexOfEdit = inputs.indexOf("Edit");
                    inputs1.add(inputs.get(indexOfEdit + 12));

                    eventController.editEvent(inputs1, controllerContainer.getCalendarGridController());
                }
                else {
                    eventController.createEvent(inputs1);
                    gridController.displayGrid();
                }
            } catch (StringIndexOutOfBoundsException ex){
                List<String> errList = new ArrayList<>();
                errList.add("Error1");
                eventController.createEvent(errList);
            }

        });


        c.gridy = 1;
        this.add(bottomPane, c);
    }
}
