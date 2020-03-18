package com.group_0225.views;

import java.util.ArrayList;
import java.util.List;

public class EditMemoView extends CalendarView {
    /**Collect the user's inputs for editing a memo
     *
     * @param outputs the strings that need to be displayed to the user.
     * @return the user's inputs
     */
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();

        System.out.println("Memo:\n" + outputs.get(0));

        return inputs;
    }
}
