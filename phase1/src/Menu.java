import java.util.List;

/**
 * This class is for a menu presented to the user. A menu has lines that are printed to the screen and within
 * those lines are options that the user can select.
 *
 * @author Daniel Shoichet
 */
public class Menu {
    private int numOptions;
    private List<String> contents;

    /**
     * Creates an instance of Menu.
     *
     * @param contents the lines that are eventually printed to the screen.
     * @param numOptions the number of options available for the user to select.
     */
    public Menu(List<String> contents, int numOptions){
        this.contents = contents;
        this.numOptions = numOptions;
    }

    /**
     *
     * @return the number of options available for the user to select.
     */
    public int getNumOptions() {
        return numOptions;
    }

    /**
     *
     * @return the lines that are eventually printed to the screen.
     */
    public List<String> getContents() {
        return contents;
    }
}
