package UserPackage;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class UserGateway {

    private File file = new File("userFile.ser");

    /**
     * Takes A Linked List of user objects and saves them to a.ser file.
     * @param list: The linked list of users to be saved
     */
    public void saveUserMap(Map<Integer, User> list){
        //File userManagerFilePath = new File("userFile.ser");
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+file.getPath());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * Takes a String path to the .ser file where the Linked List of Users is stored and
     * Deserializes it to return a Linked List of Users to the program
     */
    public Map<Integer, User> readUserMap(){
        Map<Integer, User> userMap = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userMap = (HashMap<Integer, User>) in.readObject();
            in.close();
            fileIn.close();
            return userMap;
        } catch (FileNotFoundException f) {
            return null;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("List class not found");
            c.printStackTrace();
            return null;
        }
    }
}
