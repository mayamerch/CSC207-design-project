package MessagePackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ChatroomController {
    private ArrayList<Chatroom> chats;
    private ChatroomGateway gateway;

    /**
     * Creates an instance of ChatroomController that contains all the recorded conversations.
     * Reads in existing saved Chatrooms from ChatroomDataFile.txt
     */
    public ChatroomController() throws FileNotFoundException {
        this.gateway = new ChatroomGateway();
        this.chats = gateway.makeChats();
    }

    /**
     * saves chats to ChatroomDataFile. Should be executed before program exits.
     * @throws IOException if writing to file was unsuccessful
     */
    public void saveChats() throws IOException {
        this.gateway.writeChatsToFile(this.chats);
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
                System.out.println("Your chat has been sent.");
                return;
            }
        }
        c = createNewChatRoom(userlist, senderUserID);
        c.sendMessage(message, senderUserID);
        chats.add(c);
        System.out.println("Your chat has been sent.");
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
