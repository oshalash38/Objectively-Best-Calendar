package com.group_0225.ui.core;

import com.group_0225.controller.AlertController;
import com.group_0225.controller.ControllerContainer;
import com.group_0225.ui.common.calendar.CalendarLayoutPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This panel displays a list of notifications to the user
 */
public class NotificationListPanel extends CalendarLayoutPanel {
    /**
     * Creates a NotificationListPanel instance
     * @param controllerContainer a ControllerContainer instance
     */
    public NotificationListPanel (ControllerContainer controllerContainer){
        super(controllerContainer);
    }
    @Override
    protected void buildPanel(List<String> inputs) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.weightx = 1;
        // Source: https://stackoverflow.com/questions/39348314/set-a-title-for-a-jpanel-on-eclipse



        JPanel parent = new JPanel(new GridBagLayout());
        parent.setBackground(Color.darkGray);

        if(inputs.size() == 0){
            buildTitle(c, "You have no notifications!");
            return;
        }
        String title = viewModel.get("Notifications");
        Border border = BorderFactory.createTitledBorder(title);
        this.setBorder(border);
        List<JButton> buttons = new ArrayList<>();
        AlertController alertController = controllerContainer.getAlertController();

        for(int i = 0; i<inputs.size(); i+=5){
            c.gridy = i;
            JButton notification = new JButton(inputs.get(i));
            int finalI = i;
            notification.addActionListener(e -> {alertController.displayNotifications(inputs.subList(finalI, finalI +5)); alertController.removeNotification(finalI/5);});

            parent.add(notification, c);
            buttons.add(notification);
        }


        c.fill = GridBagConstraints.BOTH;
        JScrollPane panel = new JScrollPane(parent);

        this.add(panel, c);



//        for(String str: inputs){
//            System.out.println(str);
//        }

    }
}
