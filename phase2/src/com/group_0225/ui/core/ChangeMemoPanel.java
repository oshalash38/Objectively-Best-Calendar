package com.group_0225.ui.core;

import com.group_0225.controller.ControllerContainer;
import com.group_0225.controller.MemoController;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This panel allows users to change memos
 */
public class ChangeMemoPanel extends CalendarLayoutPanel {
    public ChangeMemoPanel(ControllerContainer controllerContainer) {
        super(controllerContainer);
    }

    /**
     *
     * @param inputs index 0 -> curr/all
     *
     */
    @Override
    protected void buildPanel(List<String> inputs) {
        MemoController memoController = controllerContainer.getMemoController();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        JPanel bottomPane = new JPanel(new GridBagLayout());
        // Source: https://stackoverflow.com/questions/39348314/set-a-title-for-a-jpanel-on-eclipse
        String title = viewModel.get("editMemos");
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);



        if (inputs.get(0).equals("Current")){
            int current = inputs.indexOf("Current");
        }

        int memoStrings = inputs.indexOf("MemoStrings");
        int memoIDs = inputs.indexOf("MemoIDs");
        int i = memoStrings + 1;
        List<JTextArea> jTextAreas = new ArrayList<>();
        for (; i < memoIDs; i++){
            JTextArea textArea = addTextArea(inputs.get(i), bottomPane, i, c);
            jTextAreas.add(textArea);
        }
        Button save = addButton(c, bottomPane, i, viewModel.get("Save"));


        addLabel(c, bottomPane, i + 2, viewModel.get("Empty = Delete"));

        JLabel success = addLabel(c, bottomPane, i + 3, "");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, String> memos = new HashMap<>();
                int k = memoIDs + 1;
                int l = memoStrings + 1;
                for (JTextArea textArea : jTextAreas){
                    if (!textArea.getText().equals(inputs.get(l))){
                        if (textArea.getText().equals("")){
                            memoController.deleteMemo(inputs.get(k));
                        } else {
                            memos.put(Integer.parseInt(inputs.get(k)), textArea.getText());
                        }
                    }
                    k++;
                    l++;
                }
                if (inputs.get(0).equals("Current")){
                    memoController.editMemosCurrEvent(inputs.get(1), memos);
                } else {
                    memoController.editMemosAllEvents(memos);
                }
                success.setText(viewModel.get("ModificationC"));
//                addLabel(c, bottomPane, finalI + 4, viewModel.get("ModificationC"));
            }
        });

        c.gridy = 2;
        this.add(bottomPane, c);
    }
}
