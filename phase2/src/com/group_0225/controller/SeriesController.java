package com.group_0225.controller;

import com.group_0225.manager.SeriesManager;
import com.group_0225.entities.Timing;
import com.group_0225.ui.common.util.PanelInfo;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.entities.CalendarData;

import java.util.Arrays;
import java.util.List;

public class SeriesController extends CalendarController{
    /**
     * Constructor that sets up references to required info and presenter rules
     * @param data a CalendarData instance
     * @param p a UIPresenter instance
     */
    private SeriesManager sm;
    public SeriesController(CalendarData data, UIPresenter p){
        super(data, p);
        sm = new SeriesManager();
    }
    public void createSeriesFromScratch(/*parameters to be added when CreateSeriesFromScratchPanel is finalized*/){
        //presenter method call
        //call a method again if input is wrong
        //call sm method
    }
    public void createSeriesFromEvents(/*parameters to be added when CreateSeriesFromEvents is finalized*/){
        //presenter method
        //call a method again if input is wrong
        //call sm method
    }
    private void pushCreateSeriesFromScratchScreen(List<String> args){
        presenter.displayPanel(new PanelInfo("CreateSeriesFromScratchPanel",args, true));
    }
    public void createSeriesFromScratchScreen(){
        pushCreateSeriesFromScratchScreen(Arrays.asList(""));
    }
    private boolean parsable(List<String> lst) {
        int i;
        for (String s : lst) {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyNumEvents(int i) {
        return i >= 2;
    }
    private boolean verifyStartEnd(Timing t){
        return t.getStart().isBefore(t.getEnd());   }

    private boolean verifyFrequency(int i) {
        if (!(i>=1 && i<=5)){
            System.out.println("Your frequency selection was incorrect.");}
        return i >= 1 && i <= 5;
    }

    private boolean verifyDuration(List<Integer> lst) {
        if (!((0 <= lst.get(0)) && (6 >= lst.get(0)) && (lst.get(1) >= 0) && (lst.get(1) <= 23)
                && (lst.get(2) >= 0) && (lst.get(2) <= 59))){
            System.out.println("Your duration selection was incorrect.");
        }
        return (0 <= lst.get(0)) && (6 >= lst.get(0)) && (lst.get(1) >= 0) && (lst.get(1) <= 23)
                && (lst.get(2) >= 0) && (lst.get(2) <= 59);
    }

    private boolean verifyStartDate(List<Integer> lst) {
        if (!(lst.get(1) >= 1 && lst.get(1) <= 12 && lst.get(2) >= 1 && lst.get(2) <= 31
                && lst.get(3) >= 0 && lst.get(3) <= 23 && lst.get(4) >= 0 && lst.get(4) <= 59)){
            System.out.println("Your start date selection was incorrect.");
        }
        return lst.get(1) >= 1 && lst.get(1) <= 12 && lst.get(2) >= 1 && lst.get(2) <= 31
                && lst.get(3) >= 0 && lst.get(3) <= 23 && lst.get(4) >= 0 && lst.get(4) <= 59;
    }

    private boolean verifyDurationLTFreq(List<Integer> lst) {
        //lst contains 8 numbers (the selected duration appended to the selected frequency)
        boolean ret;
        switch (lst.get(0)) {
            case 1:
                ret =  lst.get(1) < 1 && lst.get(2) < 1;
                break;
            case 2:
                ret =  lst.get(1) < 1;
                break;
            default:
                ret =  true;
                break;
        }
        if (!ret) {
            System.out.println("The duration of each event is longer than the frequency.");
        }  return ret;
    }

}
