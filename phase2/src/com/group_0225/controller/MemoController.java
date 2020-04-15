package com.group_0225.controller;

import com.group_0225.entities.CalendarData;
import com.group_0225.manager.EventManager;
import com.group_0225.manager.MemoManager;
import com.group_0225.ui.common.util.UIPresenter;
import com.group_0225.ui.common.util.UIUpdateInfo;

import java.util.ArrayList;
import java.util.List;

public class MemoController extends CalendarController{

    private MemoManager memoManager;
    private EventManager eventManager;
    public MemoController(CalendarData data, UIPresenter presenter) {
        super(data, presenter);
        memoManager = new MemoManager();
        eventManager = new EventManager();
    }

    public void createMemo(String memo, List<String> eventNames){
        memoManager.CreateMemo(data, memo, data.getEventsByNames(eventNames));
    }

    public void pushCreateMemo(){
        // TODO: This is duplicate code from MessagingManager, should eliminate duplicates.
        List<String> events = new ArrayList<>();
        events.addAll(eventManager.getNames(data.getCurrUserEvents()));
        presenter.updateUI(new UIUpdateInfo("dialog", events, "CreateMemoPanel"));
    }
}
