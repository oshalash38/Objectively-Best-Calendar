package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StartupView extends CalendarView {
    @Override
    public List<String> activateView(List<String> outputs) {
        List<String> inputs = new ArrayList<>();
        int input;
        System.out.println("CALENDAR V1\n===============\n1. Login\n2. Create new user\n3. Exit");

        try {
            input = Integer.parseInt(in.nextLine());
            if(!((1 <= input) && (input <= 3))){
                throw new InputMismatchException();
            }
        }catch (Exception e){
            System.out.println("Entry failed. Please Try Again.\n==================================");
            return activateView(outputs);
        }
        inputs.add(input+"");
        return inputs;
    }
}
