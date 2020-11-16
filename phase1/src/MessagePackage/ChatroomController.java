package MessagePackage;

import EventPackage.EventManager;

import java.util.ArrayList;

public class ChatroomController {
    public ArrayList<Chatroom> chats;

    /**
     * Creates an instance of ChatroomController that contains all the recorded conversations (empty at first)
     */
    public ChatroomController(){
        this.chats = new ArrayList<Chatroom>();
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
     * Sends a message in a new Chatroom
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person sending the Chat
     */
    public void sendNewChat(ArrayList<Integer> userlist, int senderUserID, String message){
        Chatroom c = createNewChatRoom(userlist, senderUserID);
        c.sendMessage(message, senderUserID);
    }

    /**
     * Sends a message in an existing Chatroom
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person sending the Chat
     */
    public void sendExistingChat(ArrayList<Integer> userlist, int senderUserID, String message){
        Chatroom chatroom = new Chatroom(userlist);
        for(Conversation c: chats){
            if(c.equals(chatroom)); // if chatroom already exists
            c.sendMessage(message, senderUserID);
        }
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

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("");
        for (Chatroom c: this.chats){
            s.append(c.toString());
            s.append("\n\n");
        }
        return s.toString();
    }

}
