import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author ABHIJOY MANDAL
 */
public class DatabaseManager implements Serializable {


    /** A mapping of usernames to Users. */
    private Map<String, User> users;
    /**
     * Creates a new empty UserManager.
     *
     * @throws IOException could not load file
     * @throws ClassNotFoundException the file may be corrupted with data of different type
     */
    public DatabaseManager(String filePath) throws ClassNotFoundException, IOException {
        users = new HashMap<String, User>();


        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();
        }
    }



    public void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the Map
            users = (Map<String, User>) input.readObject();
            input.close();
        } catch (IOException ex) {
            System.out.println("AHHHHHH... not again");
        }
    }

    /**
     * Adds record to this StudentManager.
     *
     * @param record a record to be added.
     */
    public void add(User record) {
        users.put(record.getUsername(), record);

    }

    /**
     * Writes the students to file at filePath.
     *
     * @param filePath the file to write the records to
     *
     */
    public void saveToFile(String filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        try {
            // serialize the Map
            output.writeObject(users);
            output.close();
        }
        catch(IOException ex){
            System.out.println("Failed to save to file");}
    }

    public Map<String, User> getUsers() { return users; }

    public User findUser(String username){ return users.get(username);}
}
