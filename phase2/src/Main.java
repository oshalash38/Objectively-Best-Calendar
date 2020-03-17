import ui.core.CalendarPanel;

import javax.swing.*;
import java.io.IOException;

/**
 * The main class
 *
 * @author Team effort
 */
public class Main {
    public static void main(String[] args){
//        Controller myController = new Controller();
//        myController.START();

        //UNCOMMENT TO SEE BAD GUI!!!
        JFrame uiTest = new JFrame();
        uiTest.add(new CalendarPanel());
        uiTest.setSize(800, 600);
        uiTest.setVisible(true);
        uiTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
