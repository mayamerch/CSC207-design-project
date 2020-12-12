package MessagePackage;

import EventPackage.EventUseCases.EventManager;
import UserPackage.User;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ChatroomGateway {
    private File chatroomDataFile;
    private ArrayList<Chatroom> chatrooms;

    /**
     * create ChatroomGateway which saves data from ChatroomController into ChatroomDataFile
     */
    public ChatroomGateway() {
        this.chatroomDataFile = new File("src/MessagePackage/ChatroomDataFile.ser");
        try  {
            if(chatroomDataFile.createNewFile())
                chatrooms = new ArrayList<Chatroom>();
            else
                readChatsObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveChatsObject(ArrayList<Chatroom> chats) {
        try {
            this.chatrooms = chats;
            FileOutputStream fileOut = new FileOutputStream(this.chatroomDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chats);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+this.chatroomDataFile.getPath());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readChatsObject(){
        try {
            FileInputStream fileIn = new FileInputStream(this.chatroomDataFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            chatrooms = (ArrayList<Chatroom>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            chatrooms = new ArrayList<Chatroom>();
        } catch (IOException | ClassNotFoundException e)
        {
            chatrooms = new ArrayList<Chatroom>();
        }
    }

    public ArrayList<Chatroom> getChatrooms() {
        readChatsObject();
        return chatrooms;
    }

}
