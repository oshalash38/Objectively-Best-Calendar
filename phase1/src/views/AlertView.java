package views;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class AlertView extends CalendarView{

    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        for(String s: outputs){
            System.out.println(s);
        }
        System.out.println("==========================================================");
        return inputs;
    }
}
