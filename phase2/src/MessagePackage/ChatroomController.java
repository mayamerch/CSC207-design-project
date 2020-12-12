package MessagePackage;

import EventPackage.EventEntities.Event;
import EventPackage.EventEntities.MultiSpeakerEvent;
import EventPackage.EventEntities.SpeakerEvent;
import EventPackage.EventUseCases.EventManager;
import UserPackage.User;
import UserPackage.UserManager;
import UserPackage.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
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
        this.chats = gateway.getChatrooms();

    }

    public UserManager getUserManager() {
        return userManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ArrayList<Chatroom> getChats() {
        return chats;
    }

    /**
     * saves chats to ChatroomDataFile. Should be executed before program exits.
     *
     * @throws IOException if writing to file was unsuccessful
     */
    public void saveChats(){
        this.gateway.saveChatsObject(this.chats);
    }

    /**
     * Returns true if a Chatroom does not already exist
     *
     * @param userlist     a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public boolean canCreateNewChatRoom(ArrayList<Integer> userlist, int senderUserID) { //ArrayList<Integer> userlist
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
                return true; // organizers can message everyone
            }

            /*
            else if(sender.getType() == UserType.SPEAKER){
                for(Event e: eventManager.speakingAt(senderUserID)){
                    if(e.getEventAttendees().contains(user)){
                        return true; // speakers can message people at their events
                    }
                }
            }*/

            else if (!recipient.getFriendsList().contains(senderUserID)) { // if someone is not a friend of the sender
                return false; // can't send message to someone who isn't your friend
            }
        }
        return true;
    }

    /**
     * Creates and returns a new Chatroom, if possible. Returns a null Chatroom if not.
     *
     * @param userlist     a list of all users within the chat
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
     *
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



