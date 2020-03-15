package ui.common;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoField;

public class CalendarComponent extends JPanel {

    JPanel[][] calendarComponent;

    public CalendarComponent(int month) {
        super(new GridBagLayout());

        setUpCalendar(month);
    }

    private void setUpCalendar(int month) {
        calendarComponent = new JPanel[7][6];

        LocalDateTime tempLocal = LocalDateTime.of(2020, month,1,1,1);
        int weekDay = tempLocal.get(ChronoField.DAY_OF_WEEK) % 7;
        YearMonth tempYearMonth = YearMonth.of(2020, month);
        int monthLength = tempYearMonth.lengthOfMonth();

        int currDay = 1;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.1;
        c.weightx = 1;

        this.add(new Label("Sunday"),c);
        this.add(new Label("Monday"),c);
        this.add(new Label("Tuesday"),c);
        this.add(new Label("Wednesday"),c);
        this.add(new Label("Thursday"),c);
        this.add(new Label("Friday"),c);
        this.add(new Label("Saturday"),c);

        c.weighty = 1;

        for(int y = 0; y < 6; y++){
            if (currDay > monthLength)
                break;

            c.gridy = y + 1;

            for (int x = 0; x < 7; x ++) {
                JPanel timeComponent;

                if ((y == 0 && x < weekDay) || (currDay > monthLength)){
                    timeComponent = new JPanel();
                } else {
                    timeComponent = new CalendarTimeComponent(currDay);
                    currDay++;
                }

                calendarComponent[x][y] = timeComponent;
                this.add(timeComponent, c);
            }
        }
    }

}
