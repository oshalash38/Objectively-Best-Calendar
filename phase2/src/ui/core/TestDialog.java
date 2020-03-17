package ui.core;

import javax.swing.*;
import java.awt.*;

public class TestDialog extends JDialog {

    public TestDialog(JFrame frame, String name) {
        super(frame, name);

        this.add(new Label("POGGERS IT WORKS"));
        this.setSize(200,200);
    }

}
