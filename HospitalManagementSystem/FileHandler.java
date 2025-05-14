
import java.io.*;
import java.util.*;

public class FileHandler {
    // Generic save: will overwrite or create
    public static void saveData(Object data, String filepath) {
        try (ObjectOutputStream oos = 
                new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving to "+filepath+": "+e.getMessage());
        }
    }

    // Generic load for Lists
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String filepath) {
        File f = new File(filepath);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = 
                new ObjectInputStream(new FileInputStream(filepath))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading from "+filepath+": "+e.getMessage());
            return new ArrayList<>();
        }
    }
}
