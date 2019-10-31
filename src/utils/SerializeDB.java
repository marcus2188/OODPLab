package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Generic Serialize DB class that reads and writes arraylist of entities to dat file
Entity classes need to implement Serializable
 */
public class SerializeDB {
    public static List readSerializedObject(String filename) {
        List details = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            details = (ArrayList) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return details;
    }

    public static void writeSerializedObject(String filename, ArrayList list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
