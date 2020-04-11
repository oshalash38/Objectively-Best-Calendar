package com.group_0225.controller;

import com.group_0225.manager.EventManager;
import com.group_0225.manager.SeriesManager;
import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.UIUpdateInfo;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.entities.CalendarData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeriesController extends CalendarController{
    /**
     * Constructor that sets up references to required info and presenter rules
   Yea  * @param data a CalendarData instance
     * @param p a UIPresenter instance
     */
    private SeriesManager sm;
    private EventManager em;
    public SeriesController(CalendarData data, UIPresenter p){
        super(data, p);
        sm = new SeriesManager();
        em = new EventManager();
    }
    public void createSeriesFromScratch(List<String> input){

        //0 series name must be non-empty string
        //1 string int from 1-5 determining frequency
        //2 start date (day-3ltrmonth-YYYY) (ex. 8-Apr-2020)
        //3 start time (HH:MM:SS)
        //4 string int from 0-6 of duration days
        //5 duration time (HH:MM:SS)
        //6 num events string int
        if (input.get(0).equals("") || input.get(2).equals("")){
            pushCreateSeriesFromScratchScreen(Collections.singletonList("All fields must be completed."));
        }
        else if (!verifyDurationLTFreq(input.get(4),input.get(5),input.get(1))){
            pushCreateSeriesFromScratchScreen(Collections.singletonList("The duration you chose was longer than your frequency."));
        }

        else{
            String[] split = input.get(2).split("-");
            sm.createSeries(data,input.get(0),input.get(1),input.get(2),Months.get(split[1]),input.get(3),input.get(4),input.get(5), input.get(6),em);
            pushCreateSeriesFromScratchScreen(Collections.singletonList("Series created!"));
        }

    }
    public void createSeriesFromEvents(/*parameters to be added when CreateSeriesFromEvents is finalized*/){
        //presenter method
        //call a method again if input is wrong
        //call sm method
    }
    private void pushCreateSeriesFromScratchScreen(List<String> args){
        presenter.updateUI(new UIUpdateInfo("dialog",args, "CreateSeriesScratchPanel"));
    }
    public void createSeriesFromScratchScreen(){
        pushCreateSeriesFromScratchScreen(Collections.singletonList(""));
    }
    public void createSeriesChoiceScreen(){presenter.updateUI(new UIUpdateInfo("dialog",new ArrayList<>(),"SeriesChoicePanel"));}

    private boolean verifyDurationLTFreq(String durationDays, String durationHMS, String freq) {
        int days = Integer.parseInt(durationDays);
        int hours = Integer.parseInt(durationHMS.substring(0,2));
        int minutes = Integer.parseInt(durationHMS.substring(3,5));
        int seconds = Integer.parseInt(durationHMS.substring(6,8));
        switch(Integer.parseInt(freq) ){
            case 1: //hourly
                return hours == 0 && days == 0;
            case 2: //daily
                return days == 0;
            default:
                return true;
        }
    }

}
