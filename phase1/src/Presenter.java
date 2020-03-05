import views.CalendarView;
import views.UIViews;
import views.ViewModel;

import java.util.*;



/**
 * This class controls the information that is pushed to the user.
 *
 * @author Daniel Shoichet
 */
public class Presenter {
    private ViewModel myModel = new ViewModel();

    public List<String> displayView(UIViews view, List<String> info) {

        CalendarView current = myModel.views.get(view);
        return current.activateView(info);
    }


}
