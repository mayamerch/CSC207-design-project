package MessagePackage;

import EventPackage.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatroomController {
    private ArrayList<Chatroom> chats;

    /**
     * Creates an instance of ChatroomController that contains all the recorded conversations (empty at first)
     */
    public ChatroomController(){
        this.chats = new ArrayList<Chatroom>();
    }

    /**
     * Used in Gateway to create instance of ChatroomController from saved chats
     * @param chats ArrayList of Chatroom from Gateway
     */
    public ChatroomController(ArrayList<Chatroom> chats){
        this.chats = chats;
    }

    public ArrayList<Chatroom> getChats() {
        return chats;
    }

    /**
     * Returns true if a Chatroom does not already exist
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public boolean canCreateNewChatRoom(ArrayList<Integer> userlist, int senderUserID){
        Chatroom c = new Chatroom(userlist);
        if (chats.contains(c)) {
            return false;
        }
        for (int user : userlist) {
            if (!c.canRead(user) || !c.canSend(user)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates and returns a new Chatroom, if possible. Raises an Error if not.
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public Chatroom createNewChatRoom(ArrayList<Integer> userlist, int senderUserID) {
        if(canCreateNewChatRoom(userlist, senderUserID)){
            Chatroom c = new Chatroom(userlist);
            chats.add(c);
            return c;
        }
        else {
            throw new java.lang.Error("This Chatroom cannot be created.");
        }
    }


    /**
     * Sends a message in an existing Chat, or creates a new one if it doesn't exist
     * @param userlist of everyone you are sending the message to
     * @param senderUserID the ID of the user who is sending the broadcast
     * @param message content of the message you are sending
     */
    public void sendChat(ArrayList<Integer> userlist, int senderUserID, String message){
        Chatroom c = createNewChatRoom(userlist, senderUserID);

        for(Chatroom chatroom: chats){
            if(chatroom.equals(c)){
                c.sendMessage(message, senderUserID);
                return;
            }
        }
        c = createNewChatRoom(userlist, senderUserID);
        c.sendMessage(message, senderUserID);
        chats.add(c);
        System.out.println("Your chats has been sent.");
    }

    /**
     * Returns all chats for a given userID
     * @param userID identifies user given this userID and returns the Chatrooms they can read
     */
    public ArrayList<Chatroom> returnChatsforUserID(int userID){
        ArrayList<Chatroom> myChats = new ArrayList<>();
        for(Chatroom c: chats){
            if (c.canRead(userID)){
                myChats.add(c);
            }
        }
        return myChats;
    }

    public String myChats(int userID){
        StringBuilder s = new StringBuilder("");
        for (Chatroom c: returnChatsforUserID(userID)){
            s.append(c.format());
            s.append("\n------\n") ;
        }
        return s.toString();
    }


    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("");
        for (Chatroom c: this.chats){
            s.append(c.toString());
            s.append("\n\n") ;
        }
        return s.toString();
    }

}
