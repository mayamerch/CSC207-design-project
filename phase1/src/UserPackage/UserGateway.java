package UserPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserGateway {

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
    public LinkedList<User> readUserList(String path){
        LinkedList<User> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (LinkedList<User>) in.readObject();
            in.close();
            fileIn.close();
            return list;
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
