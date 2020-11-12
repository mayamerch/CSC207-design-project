package MessagePackage;

import EventPackage.*;
import UserPackage.*;
import java.util.ArrayList;

public class ConversationController {

    public ArrayList<Conversation> conversations;
    public EventManager eventManager;
    public Message message;

    /**
     * Creates an instance of ConversationController that contains all the recorded conversations (empty at first)
     */
    public ConversationController(ArrayList<Conversation> conversations, Message message){
        this.conversations = conversations;
        this.message = message;
    }

    /**
     * Returns true if an instance of Chatroom can be created, and creates it
     * @param userlist a list of all users within the chat
     */
    public boolean createChatRoom(ArrayList<Integer> userlist, int senderUserID) {
        Chatroom c = new Chatroom(userlist);
        if(conversations.contains(c)){
            c.sendMessage(message.getContent(), senderUserID);
            return false;
        }
        else { // create new chatroom
            for (int user : userlist) {
                if(!c.canRead(user) || !c.canSend(user)) {
                    return false;
                }
            }
        }
        conversations.add(c);
        c.sendMessage(message.getContent(), senderUserID);
        return true;
    }

    /**
     * Returns true if an instance of Broadcast can be created, and creates it
     * @param messageQueue messages being sent in the broadcast
     * @param user the user who is sending the broadcast
     * @param event the event at which all the attendees are receiving the broadcast
     */
    public boolean createBroadcast(MessageQueue messageQueue, User user, Event event){
        ArrayList<Integer> broadcasters = new ArrayList<>();

        Broadcast b = new Broadcast(messageQueue, broadcasters, event.getEventId());
        if(b.canSend(user.get_userID())){
            broadcasters.add(user.get_userID());
        }
        else{
            return false; // if user cannot send the broadcast
        }

        if(conversations.contains(b)){
            b.sendMessage(message.getContent(), user.get_userID());
            return false; // if broadcast already exists
        }
        else{ // create new broadcast
            if(b.canSend(user.get_userID())){
                conversations.add(b);
                b.sendMessage(message.getContent(), user.get_userID());
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a Broadcast for multiple talks of a speaker
     * @param speaker the broadcast is being sent to all talks this speaker is speaking at
     * @param messageQueue messages being sent in the broadcast
     */
    public void createBroadcastInAllSpeakerEvents(Speaker speaker, MessageQueue messageQueue){
        for(int eventID: speaker.getTalksList()){
            createBroadcast(messageQueue, speaker, eventManager.getEvent(eventID));
        }
    }

    /**
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
