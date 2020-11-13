package MessagePackage;

import EventPackage.*;
import UserPackage.*;
import com.sun.xml.internal.bind.v2.TODO;

import java.lang.reflect.Array;
import java.util.ArrayList;

// TODO: write in Boot file how to instantiate ConversationController and all functionality
public class ConversationController {
    public ArrayList<Conversation> conversations;
    public EventManager eventManager;
    public Message message;

    /**
     * Creates an instance of ConversationController that contains all the recorded conversations (empty at first)
     * @param conversations a list of all ongoing or previous conversations
     * @param message the message which is being sent (either in a Chatroom or a Broadcast)
     */
    public ConversationController(ArrayList<Conversation> conversations, Message message){
        this.conversations = conversations;
        this.message = message;
    }

    /**
     * Returns true if a Chatroom does not already exist
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person creating the Chatroom
     */
    public boolean canCreateNewChatRoom(ArrayList<Integer> userlist, int senderUserID) {
        Chatroom c = new Chatroom(userlist);
        if (conversations.contains(c)) {
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
            conversations.add(c);
            return c;
        } //c.sendMessage(message.getContent(), senderUserID);
        else {
            throw new java.lang.Error("This Chatroom cannot be created.");
        }
    }

    /**
     * Sends a message in a new Chatroom
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person sending the Chat
     */
    public void sendNewChat(ArrayList<Integer> userlist, int senderUserID){
        Chatroom c = createNewChatRoom(userlist, senderUserID);
        c.sendMessage(message.getContent(), senderUserID);
    }

    /**
     * Sends a message in an existing Chatroom
     * @param userlist a list of all users within the chat
     * @param senderUserID the userID of the person sending the Chat
     */
    public void sendExistingChat(ArrayList<Integer> userlist, int senderUserID){
        Chatroom chatroom = new Chatroom(userlist);
        for(Conversation c: conversations){
            if(c.equals(chatroom)); // if chatroom already exists
            c.sendMessage(message.getContent(), senderUserID);
        }
    }

    /************************************************************************************************************/

    /**
     * Returns true if a Broadcast does not already exist and can be created
     * @param mq the message to be sent in the broadcast
     * @param user the user who will be sending the broadcast
     * @param event the event whose attendees the broadcast will be sent to
     */
    public boolean canCreateNewBroadCast(MessageQueue mq, User user, Event event) {
        // TODO: is there a better way to do this so it's not repeated:
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        Broadcast b = new Broadcast(mq, broadcasters, event.getEventId());
        if (conversations.contains(b)) {
            return false;
        }
        for (int broadcaster : broadcasters) {
            if (!b.canSend(broadcaster)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates and returns a new Broadcast, if possible. Raises an Error if not.
     * @param mq the message to be sent in the broadcast
     * @param user the user who will be sending the broadcast
     * @param event the event whose attendees the broadcast will be sent to
     */
    public Broadcast createNewBroadcast(MessageQueue mq, User user, Event event) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        if(canCreateNewBroadCast(mq, user, event)){
            Broadcast b = new Broadcast(mq, broadcasters, event.getEventId());
            conversations.add(b);
            return b;
        }
        else {
            throw new java.lang.Error("This Broadcast cannot be created.");
        }
    }

    /**
     * Sends a message in a Broadcast
     * @param user the user who is sending the broadcast
     * @param event the event at which all the attendees are receiving the broadcast
     */
    public void sendNewBroadcast(MessageQueue mq, User user, Event event){
        ArrayList<Integer> broadcasters = new ArrayList<>();
        Broadcast b = createNewBroadcast(mq, user, event);
        b.sendMessage(message.getContent(), user.get_userID());
    }

    /**
     * Sends a message in an existing Broadcast
     * @param user the user who is sending the broadcast
     * @param event the event at which all the attendees are receiving the broadcast
     */
    public void sendExistingBroadcast(MessageQueue mq, User user, Event event){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        Broadcast broadcast = new Broadcast(mq, broadcasters, event.getEventId());
        for(Conversation c: conversations){
            if(c.equals(broadcast)); // if chatroom already exists
            c.sendMessage(message.getContent(), user.get_userID());
        }
    }

    /**
     * Sends a Broadcast for multiple talks of a speaker
     * @param speaker the broadcast is being sent to all talks this speaker is speaking at
     * @param messageQueue messages being sent in the broadcast
     */
    public void createBroadcastInAllSpeakerEvents(Speaker speaker, MessageQueue messageQueue){
        for(int eventID: speaker.getTalksList()){
            createNewBroadcast(messageQueue, speaker, eventManager.getEvent(eventID));
        }
    }

    /**
     * Returns all conversations for a given userID
     * @param userID identifies user given this userID and returns their conversations they can read
     */
    public ArrayList<Conversation> returnConversationsforUserID(int userID){
        ArrayList<Conversation> allConversations = new ArrayList<>();
        for(Conversation c: conversations){
            if (c.canRead(userID)){
                allConversations.add(c);
            }
        }
        return allConversations;
    }

}
