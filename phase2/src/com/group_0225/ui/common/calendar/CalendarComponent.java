package com.group_0225.ui.common.calendar;

import com.group_0225.controller.CalendarGridController;
import com.group_0225.controller.ControllerContainer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends CalendarLayoutPanel and its functionality
 */
public class CalendarComponent extends CalendarLayoutPanel {

    List<JPanel> calendarComponent;

    /**
     * Creates a new CalendarComponent instance
     * @param controllerContainer a ControllerContainer instance
     */
    public CalendarComponent(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    @Override
    protected void buildPanel(List<String> inputs) {

        CalendarGridController gridController = controllerContainer.getCalendarGridController();

        int indexOfDisplay = inputs.indexOf("Display");
        String displayDay = inputs.get(indexOfDisplay + 1);
        String displayMonth = inputs.get(indexOfDisplay + 2);
        String displayYear = inputs.get(indexOfDisplay + 3);

        int indexOfCurrent = inputs.indexOf("Current");
        String currentMonth = inputs.get(indexOfCurrent + 2);
        String currentYear = inputs.get(indexOfCurrent + 3);

        int indexOfEvents = inputs.indexOf("Events");
        int indexOfAlerts = inputs.indexOf("Alerts");

        int indexOfWeather = inputs.indexOf("Weather");
        List<String> weatherInput = inputs.subList(indexOfWeather + 1, inputs.size());

        calendarComponent = new ArrayList<>();

        LocalDateTime tempLocal = LocalDateTime.of(2020, Integer.parseInt(displayMonth),1,1,1);
        int weekDay = tempLocal.get(ChronoField.DAY_OF_WEEK) % 7;
        YearMonth tempYearMonth = YearMonth.of(Integer.parseInt(displayYear), Integer.parseInt(displayMonth));
        int monthLength = tempYearMonth.lengthOfMonth();

        int currDay = 1;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.1;
        c.weightx = 1;

        this.addDates();

        c.weighty = 1;

        for(int y = 0; y < 6; y++){
            if (currDay > monthLength)
                break;

            c.gridy = y + 1;

            for (int x = 0; x < 7; x ++) {
                JPanel timeComponent;

                if ((y == 0 && x < weekDay) || (currDay > monthLength)){
                    timeComponent = new JPanel();
                    timeComponent.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                } else {
                    boolean isCurrentDay = displayMonth.equals(currentMonth) && displayYear.equals(currentYear) && Integer.parseInt(displayDay) == currDay;

                    List<String> dayInfo = new ArrayList<>();
                    dayInfo.add(currDay + "");
                    dayInfo.add(displayMonth);
                    dayInfo.add(displayYear);
                    dayInfo.add(isCurrentDay + "");
                    dayInfo.add(inputs.get(indexOfEvents + currDay));


                    dayInfo.add(gridController.getTempForDay(weatherInput, currDay + "", displayMonth, displayYear));


                    CalendarTimeComponent timeComp = new CalendarTimeComponent(controllerContainer);
                    timeComp.updatePanel(dayInfo);

                    timeComponent = timeComp;
                    currDay++;
                }

                calendarComponent.add(timeComponent);
                this.add(timeComponent, c);
            }
        }
    }

    private void addDates() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.1;
        c.weightx = 1;

        String[] dayOfTheWeek = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for(String day : dayOfTheWeek) {
            JPanel dayPanel = new JPanel();
            dayPanel.add(new Label(day));

            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            this.add(dayPanel, c);
        }
    }
}
