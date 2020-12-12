package MessagePackage;

import EventPackage.EventUseCases.EventManager;
import UserPackage.User;
import UserPackage.UserManager;
import UserPackage.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ChatroomController {
    private ArrayList<Chatroom> chats;
    private ChatroomGateway gateway;
    private EventManager eventManager;
    private UserManager userManager;

    /**
     * Creates an instance of ChatroomController that contains all the recorded conversations.
     * Reads in existing saved Chatrooms from ChatroomDataFile.txt
     */
    public ChatroomController(EventManager eventManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.gateway = new ChatroomGateway();
        //this.chats = gateway.readChatsObject();
        try {
            this.chats = gateway.makeChats();
        } catch (FileNotFoundException f) {
            this.chats = new ArrayList<Chatroom>();
        }

    }

    /**
     * @return this User Manager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * @return this Event Manager
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * @return an ArrayList of all chats
     */
    public ArrayList<Chatroom> getChats() {
        return chats;
    }

    /**
     * saves chats to ChatroomDataFile. Should be executed before program exits.
     * @throws IOException if writing to file was unsuccessful
     */
    public void saveChats(){
        //this.gateway.writeChatsToFile(this.chats);
        try {
            this.gateway.writeChatsToFile(this.chats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if a Chatroom does not already exist
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public boolean canCreateNewChatRoom(ArrayList<Integer> userlist, int senderUserID) {
        User sender = userManager.getUserByID(senderUserID);
        Chatroom c = new Chatroom(senderUserID, userlist);
        if (chats.contains(c)) {
            return false;
        }
        for (int user : userlist) {
            if (!c.canRead(user) || !c.canSend(user)) {
                return false;
            }
        }
        for (int user : userlist) {
            User recipient = userManager.getUserByID(user);

            if (sender.getType() == UserType.ORGANIZER) {
                return true;
            }

            else if (!recipient.getFriendsList().contains(senderUserID)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates and returns a new Chatroom, if possible. Returns a null Chatroom if not.
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public Chatroom createNewChatRoom(ArrayList<Integer> userlist, int senderUserID) throws ArrayIndexOutOfBoundsException {
        Chatroom create = new Chatroom(senderUserID, userlist);
        if (canCreateNewChatRoom(userlist, senderUserID)) {
            chats.add(create);
            return create;
        }
        throw new NullPointerException();
    }


    /**
     * Sends a message in an existing Chat, or creates a new one if it doesn't exist
     * @param userlist of everyone you are sending the message to
     * @param senderUserID the ID of the user who is sending the broadcast
     * @param message content of the message you are sending
     * @return the output String message indicating success or not
     */
    public String sendChat(ArrayList<Integer> userlist, int senderUserID, String message) {
        try{
            Chatroom c = createNewChatRoom(userlist, senderUserID);

            for(int user: userlist){
                User sender = userManager.getUserByID(senderUserID);
                User receiver = userManager.getUserByID(user);
                if(!receiver.getFriendsList().contains(senderUserID)){
                    return "You are not friends with User " + user + ". Failed to send."; // can't send a chat to someone who's not your friend
                }
            }

            for (Chatroom chatroom : chats) {
                if (chatroom.equals(c)) {
                    chatroom.sendMessage(message, senderUserID);
                    return "Message sent!";
                }
            }

            c.sendMessage(message, senderUserID);
            chats.add(c);
            return "Message sent!";
        }
        catch(NullPointerException n){
            return "This Chatroom could not be created. Failed to send.";
        }
    }

    /**
     * Returns all chats for a given userID
     * @param userID identifies user given this userID and returns the Chatrooms they can read
     */
    public ArrayList<Chatroom> returnChatsforUserID(int userID) {
        ArrayList<Chatroom> myChats = new ArrayList<>();
        for (Chatroom c : chats) {
            if (c.canRead(userID)) {
                myChats.add(c);
            }
        }
        return myChats;
    }

    /**
     * Returns all chats for a given userID
     * @param userID identifies user given this userID and returns the Chats they can read
     */
    public String myChats(int userID) {
        StringBuilder s = new StringBuilder("");
        if (returnChatsforUserID(userID).size() == 0) {
            return "You have no messages!";
        }
        for (Chatroom c : returnChatsforUserID(userID)) {
            s.append(c.format());
            s.append("\n------\n");
        }
        return s.toString();
    }

    /**
     * Returns all broadcasts from a given userID
     * @param myID the recipient's userID
     * @param senderID the User's ID whose messages you are looking for
     */
    public String getChatsFromUser(int myID, int senderID){
        StringBuilder s = new StringBuilder("");
        if (returnChatsforUserID(myID).size() == 0) {
            return "You have no messages from User " + senderID + "!";
        }
        for(Chatroom chatroom: returnChatsforUserID(myID)){
            if(chatroom.getSenderID() == senderID){
                s.append(chatroom.format());
                s.append("\n------\n");
            }
        }
        if (s.equals("")) {
            return "You have no messages from User " + senderID + "!";
        }
        return s.toString();
    }

    /**
     * @return string to be parsed by Gateway classes
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (Chatroom c : this.chats) {
            s.append(c.toString());
            s.append("\n\n");
        }
        return s.toString();
    }
}



