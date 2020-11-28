package MessagePackage;

import UserPackage.User;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ChatroomGateway {
    private File chatroomDataFile;
    private boolean serialize = true; //if set to true, file is set to ser file in constructor, feel free to delete if we only serialize

    /**
     * create ChatroomGateway which saves data from ChatroomController into ChatroomDataFile
     */
    public ChatroomGateway() {
        //if serialize is a placeholder, we can delete it if we know that serializing works well
        //if we delete it, we'll delete all the non serializing methods
        if (serialize)
            this.chatroomDataFile = new File("src/MessagePackage/ChatroomDataFile.ser");
        else
            this.chatroomDataFile = new File("src/MessagePackage/ChatroomDataFile.txt");
    }

    public void saveChatsObject(ArrayList<Chatroom> chats) {
        try {
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
    public ArrayList<Chatroom> readChatsObject(){
        ArrayList<Chatroom> chats = null;
        try {
            FileInputStream fileIn = new FileInputStream(this.chatroomDataFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            chats = (ArrayList<Chatroom>) in.readObject();
            in.close();
            fileIn.close();
            return chats;
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

    /**
     * saves instance variable of ChatroomController chats to ChatroomDataFile
     * @param chats ArrayList of chatrooms found in ChatroomController
     * @throws IOException in case ChatroomDataFile not written to.
     */
    public void writeChatsToFile(ArrayList<Chatroom> chats) throws IOException {
        StringBuilder s = new StringBuilder("");
        for (Chatroom c: chats){
            s.append(c.toString());
            s.append("\n\n") ;
        }
        FileWriter writer = new FileWriter(this.chatroomDataFile);
        writer.write(s.toString());
        writer.close();
    }

    /**
     * Clears ChatroomDataFile to empty.
     * @throws IOException if ChatroomDataFile.txt does not exist
     */
    public void emptyChatroomDataFile() throws IOException {
        FileWriter writer = new FileWriter(this.chatroomDataFile);
        writer.write("");
        writer.close();
    }

    /**
     * Make an ArrayList of Chatrooms from ChatroomDataFile
     * @return Arraylist of Chatrooms from data in ChatroomDataFile.txt
     * @throws FileNotFoundException if ChatroomDataFile.txt does not exist
     */
    public ArrayList<Chatroom> makeChats() throws FileNotFoundException {
        ArrayList<Chatroom> chats = new ArrayList<>();
        Scanner scan = new Scanner(this.chatroomDataFile);
        scan.useDelimiter("\n\n");
        while (scan.hasNext()) {
            Chatroom c = stringToChatroom(scan.next());
            chats.add(c);
        }
        return chats;
    }

    /**
     * Make a Message from a string
     * @param s a String of data for a Message
     * @return instance of message constructed with data from String s
     */
    private Message stringToMessage(String s){
        String[] stuff = s.split("~");
        String content = stuff[1];
        Integer sender = Integer.parseInt(stuff[0]);
        return new Message(content, sender);
    }

    /**
     * Make the messages instance variable for a Chatroom
     * @param s String of data for the messages variable
     * @return ArrayList of Messages
     */

    private ArrayList<Message> stringToMessages(String s){
        ArrayList<Message> messages = new ArrayList<Message>();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split("\t");
            for (String messageStr : stuff){
                messages.add(stringToMessage(messageStr));
            }
        }
        return messages;
    }


    /**
     * Make a userList for Chatroom from a string from ChatroomDataFile
     * @param s a String representing an ArrayList of userIDs
     * @return return the userList instance variable for Chatroom
     */
    private ArrayList<Integer> stringToUserList(String s){
        ArrayList<Integer> userList = new ArrayList<Integer>();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split(", ");
            for (String userIDStr : stuff){
                userList.add(Integer.parseInt(userIDStr));
            }
        }
        return userList;
    }


    /**
     * Make a Chatroom from a string from ChatroomDataFile
     * @param s a String of data for a Chatroom
     * @return an instance of Chatroom with data from String s
     */
    private Chatroom stringToChatroom(String s){
        String[] stuff = s.split("\n");
        ArrayList<Integer> userList = stringToUserList(stuff[0]);
        ArrayList<Message> messages = stringToMessages(stuff[1]);

        return new Chatroom(userList, messages);
    }


}
