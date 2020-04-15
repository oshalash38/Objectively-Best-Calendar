package com.group_0225.ui.core;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateRepeatingAlertPanel extends CalendarLayoutPanel {
    public CreateRepeatingAlertPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {
        AlertController alertController = controllerContainer.getAlertController();

        CreateAlertCommonPanel header = new CreateAlertCommonPanel(inputs.subList(1, inputs.size()), controllerContainer,
                "CreateRepeatingAlertTitleString");

        this.add(header);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        JPanel panel = new JPanel(new GridBagLayout());
        addLabel(c, panel, 0, viewModel.get("SelectDateString"));
        JDatePickerImpl dateSelection = addCalendarPicker(c, panel, 1);
        addLabel(c, panel, 2, viewModel.get("SelectTimeString"));
        JSpinner timeSelection = addTimeSpinner(c, panel, 3);

        addLabel(c, panel, 4, viewModel.get("SelectTimeFrequencyString"));
        TextField days = addCustomTextField(c, panel, 5, " ","");
        TextField hours = addCustomTextField(c, panel, 5, ":","");
        TextField minutes = addCustomTextField(c, panel, 5, ":","");

        Button confirmButton = addButton(c, panel, 6, viewModel.get("ConfirmString"));

        confirmButton.addActionListener(e->{
            List<String> userInputs = new ArrayList<>();
            String freq = days.getText()+":"+hours.getText()+":"+minutes.getText();

        });

        c.gridy = 6;
        this.add(new Label(inputs.get(0)), c);


        c.gridy = 1;
        this.add(panel, c);

    }

    private JSpinner buildFrequencySpinner(GridBagConstraints c, JPanel panel, int yPosition){
        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "ddd:HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        c.gridy = yPosition;
        panel.add(timeSpinner, c);
        return timeSpinner;
    }

    private TextField addCustomTextField(GridBagConstraints c, JPanel bottomPane, int yPosition, String label, String text){
        c.fill = GridBagConstraints.NONE;
        c.gridy = yPosition;
        JPanel UserText = new JPanel();
        TextField field = new TextField(text);
        field.setColumns(4);
        UserText.add(new Label(label));
        UserText.add(field);
        bottomPane.add(UserText, c);
        return field;
    }
}
