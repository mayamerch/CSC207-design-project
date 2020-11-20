package UserPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserGateway {
    /**
     * Takes A Linked List of user objects and saves them to a.ser file.
     * @param list: The linked list of users to be saved
     */
    public void saveUserList(LinkedList<User> list){
        //File userManagerFilePath = new File("userFile.ser");
        try {
            FileOutputStream fileOut = new FileOutputStream("userFile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in userFile.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * Takes a String path to the .ser file where the Linked List of Users is stored and
     * De serlaizes it to return a Linked List of Users to the program
     * @param path: The path to the .ser file where the Linked List of Users is stored, String
     */
    public LinkedList<User> readUserList(String path){
        LinkedList<User> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (LinkedList<User>) in.readObject();
            in.close();
            fileIn.close();
            return list;
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
