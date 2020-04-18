package com.group_0225;

import java.io.*;


/**
 * @author ABHIJOY MANDAL
 */
public class DatabaseReader implements Serializable {
    private static final long serialVersionUID = 1L;


    public Object readFile(String filePath) throws ClassNotFoundException, IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return readFromFile(filePath);
        }
        file.createNewFile();
        return readFromFile(filePath);
    }



    public Object readFromFile(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            Object obj = input.readObject();
            input.close();
            return obj;
        } catch (IOException ex) {
        }
        return null;
    }

    /**
     * Writes the students to file at filePath.
     *
     * @param filePath the file to write the records to
     *
     */
    public void saveToFile(String filePath, Object obj) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        try {
            // serialize the Map
            output.writeObject(obj);
            output.close();
        }
        catch(IOException ex){}
    }

}
