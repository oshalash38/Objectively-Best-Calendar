package com.group_0225.ui.common.calendar;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.util.ViewModelBuilder;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * This panel forms the basis for most other panels in the UI
 */
public abstract class CalendarLayoutPanel extends JPanel {

    protected ControllerContainer controllerContainer;
    protected Map<String, String> viewModel;

    /**
     * Creates a new CalendarLayoutPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarLayoutPanel(ControllerContainer controllerContainer) {
        super(new GridBagLayout());
        this.controllerContainer = controllerContainer;
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();
    }

    /**
     * Creates a new CalendarLayoutPanel instance
     * @param layoutManager2 a LayoutManager2 instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarLayoutPanel(LayoutManager2 layoutManager2, ControllerContainer controllerContainer) {
        super(layoutManager2);
        this.controllerContainer = controllerContainer;
        ViewModelBuilder vmb = new ViewModelBuilder();
        viewModel = vmb.build();
    }

    /**
     * Builds the panel indicates from inputs
     * @param inputs information that specifies which panel to build
     */
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

    protected JComboBox<String> addDropDown(List<String> args, JPanel bottomPane, int y, GridBagConstraints constraints){
        Object[] s =  args.toArray();
        String[] sArray = Arrays.copyOf(s,s.length,String[].class);
        JComboBox<String> comboBox = new JComboBox<>(sArray);
        constraints.gridy = y;
        bottomPane.add(comboBox, constraints);
        return comboBox;
    }
    protected JCheckBox addCheckBox(String message, JPanel bottomPane, int y, GridBagConstraints constraints){
        JCheckBox tick = new JCheckBox(message);
//        label.setLabelFor(tick);
        constraints.gridy = y;
        bottomPane.add(tick, constraints);
        return tick;
    }
    protected JTextArea addTextArea(String text, JPanel bottomPane, int y, GridBagConstraints constraints){
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = y;
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setText(text);

        JScrollPane scrollPane = new JScrollPane(textArea);
        bottomPane.add(scrollPane, constraints);
        return textArea;

    }

    protected JTextArea addTextArea(GridBagConstraints c, JPanel bottomPane, int y){
        return addTextArea("", bottomPane, y, c);
    }

    protected JSpinner addNumEventsSpinner(GridBagConstraints constraints, JPanel bottomPane, int y){
        SpinnerModel numMod = new SpinnerNumberModel(2, 2, 99, 1);
        JSpinner j = new JSpinner(numMod);
        constraints.gridy = y;
        bottomPane.add(j,constraints);
        return j;
    }

    protected abstract void buildPanel(List<String> inputs);


}
