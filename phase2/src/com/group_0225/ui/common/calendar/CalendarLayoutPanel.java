package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerContainer;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class CalendarLayoutPanel extends JPanel {

    protected ControllerContainer controllerContainer;
    protected Map<String, String> viewModel;

    public CalendarLayoutPanel(LayoutManager2 layoutManager2, ControllerContainer controllerContainer) {
        super(layoutManager2);
        this.controllerContainer = controllerContainer;
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();
    }

    public void updatePanel(List<String> inputs) {
        this.removeAll();
        this.buildPanel(inputs);
    }

    protected Button addButton(GridBagConstraints c, JPanel bottomPane, int yPosition, String label){
        Button button = new Button(label);
        c.gridy = yPosition;
        bottomPane.add(button, c);
        return button;
    }

    protected void buildTitle(GridBagConstraints c, String text){
        Label titleLabel = new Label(text);
        titleLabel.setAlignment(Label.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        this.add(titleLabel, c);
    }

    protected TextField addTextField(GridBagConstraints c, JPanel bottomPane, int yPosition, String label, String text){
        c.fill = GridBagConstraints.NONE;
        c.gridy = yPosition;
        JPanel UserText = new JPanel();
        TextField field = new TextField(text);
        field.setColumns(30);
        UserText.add(new Label(label));
        UserText.add(field);
        bottomPane.add(UserText, c);
        return field;
    }

    // TODO: Cite this from (https://www.programcreek.com/java-api-examples/index.php?api=org.jdatepicker.impl.UtilDateModel)
    protected JDatePickerImpl addCalendarPicker(GridBagConstraints c, JPanel bottomPane, int yPosition) {
        Properties p = new Properties();
        p.put("text.today", "today");
        p.put("text.month", "month");
        p.put("text.year", "year");

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        c.gridy = yPosition;
        bottomPane.add(datePicker, c);
        return datePicker;
    }

    protected JLabel addLabel(GridBagConstraints c, JPanel bottomPane, int yPosition, String text){
        JLabel label1 = new JLabel(text);
        c.gridy = yPosition;
        bottomPane.add(label1, c);
        return label1;
    }

    // TODO: Cite this from Stack overflow (https://stackoverflow.com/questions/654342/is-there-any-good-and-free-date-and-time-picker-available-for-java-swing_
    protected JSpinner addTimeSpinner(GridBagConstraints c, JPanel bottomPane, int yPosition) {
        JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        c.gridy = yPosition;
        bottomPane.add(timeSpinner, c);
        return timeSpinner;
    }


    protected abstract void buildPanel(List<String> inputs);
}
