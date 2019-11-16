package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 Generic Serialize DB class that reads and writes arraylist of entities to dat file
Entity classes need to implement Serializable
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class SerializeDB {
    /** 
    * Read from file
    * @param filename The file's name
    * @return The list of objects
    */
    public static List readSerializedObject(String filename) {
        List details = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream("src/data/" + filename);
            in = new ObjectInputStream(fis);
            details = (ArrayList) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (SecurityException s) {
            s.printStackTrace();
        }

        finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return details;
    }

    /** 
    * Write to file
    * @param filename The file's name
    * @param list The data to be written
    */
    public static void writeSerializedObject(String filename, ArrayList list)  {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("src/data/"+filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        catch (SecurityException s) {
            s.printStackTrace();
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
      //   ArrayList items = (ArrayList) readSerializedObject("cineplex.dat");
       //  System.out.println(items.get(0));

    }
}
