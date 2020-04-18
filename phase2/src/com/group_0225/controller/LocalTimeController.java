package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.entities.Timing;
import com.group_0225.entities.TimingFactory;
import com.group_0225.manager.AlertManager;
import com.group_0225.manager.EventManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.ViewModelBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LocalTimeController extends CalendarController{

    public LocalTimeController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
    }

    public void backToThePresent(CalendarGridController calendarGridController){
        TimingFactory timingFactory = new TimingFactory();
        data.setLocalTime(timingFactory.createTiming(LocalDateTime.now()));

        calendarGridController.updateDisplayTime();
        calendarGridController.displayGrid();
    }

    public void pushChangeTimePanel(){
        presenter.updateUI(new UIUpdateInfo("dialog", Arrays.asList(""), "ChangeTimePanel"));
    }

    /**
     * Changes the time of the program
     * @param input : index 0: Name, index 1: Start Date, index 2: End Date,
     *              index 3: Start Time, index 4: End Time
     */
    public void changeTime(List<String> input, CalendarGridController calendarGridController) {
        TimingFactory timingFactory = new TimingFactory();
        Map<String, String> viewModel = new ViewModelBuilder().build();

        List<Integer> date = parseDate(input.get(0));
        List<Integer> time = parseTime(input.get(1));

        Timing timing = timingFactory.createTiming(date.get(2), date.get(1), date.get(0), time.get(0), time.get(1));

        data.setLocalTime(timing);

        EventManager eventManager = new EventManager();
        eventManager.updateStatus(data);

        AlertManager alertManager = new AlertManager();
        alertManager.setCurrentTime(timing.getStart());

        calendarGridController.updateDisplayTime();
        calendarGridController.displayGrid();
    }
}
