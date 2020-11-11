package MessagePackage;

import EventPackage.*;
import UserPackage.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ConversationController {
    public ArrayList<Conversation> conversations;
    public Iterator iterator;
    public EventManager eventManager;

    /**
     * Creates an instance of ConversationController that contains all the recorded conversations (empty at first)
     */
    public ConversationController(ArrayList<Conversation> conversations){
        this.conversations = conversations;
        this.iterator = conversations.iterator();
    }

    /**
     * Creates an instance of Chatroom
     * @param userlist a list of all users within the chat
     */
    public void createChatRoom(ArrayList<Integer> userlist){
        Chatroom c = new Chatroom(userlist);
    }

    /**
     * Creates an instance of Broadcast
     * @param messageQueue messages being sent in the broadcast
     * @param user the user who is sending the broadcast
     * @param event the event at which all the attendees are receiving the broadcast
     */
    public void createBroadcast(MessageQueue messageQueue, User user, Event event){
        ArrayList<Integer> broadcasters = new ArrayList<>();
        broadcasters.add(user.get_userID());

        Broadcast b = new Broadcast(messageQueue, broadcasters, event.getEventId());
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
     * @param userID identifies user given this userID and returns their conversations
     */
    public ArrayList<Conversation> returnConversationsforUserID(int userID){
        ArrayList<Conversation> allConversations = new ArrayList<>();
        while(iterator.hasNext()){ // i don't it's necessary to use an iterator at all
            Conversation c = (Conversation) iterator.next();
            if(c.getAllReaderIDs().contains(userID)){
                allConversations.add(c);
            }
        }
        return allConversations;
    }

}
