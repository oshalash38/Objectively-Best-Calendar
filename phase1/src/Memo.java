import java.io.Serializable;

public class Memo implements Serializable {
    private int id;
    private String value;

    /**
     * Constructor for a memo
     * @param id The memo's id
     * @param value The value of the memo
     */
    public Memo(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setValue(String value) { this.value = value; }
    public String getValue() { return this.value; }
    public int getID() { return this.id; }
}
