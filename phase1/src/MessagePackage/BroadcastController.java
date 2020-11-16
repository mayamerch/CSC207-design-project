package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;
import UserPackage.User;

import java.util.ArrayList;

public class BroadcastController {
    public ArrayList<Broadcast> broadcasts; // conversations in conversation controller
    public EventManager eventManager;
    public Message message;

    /**
     * Creates an instance of BroadcastManager that contains all the recorded conversations (empty at first)
     * @param message the message which is being sent
     */
    public BroadcastController(Message message){
        this.broadcasts = new ArrayList<Broadcast>();
        this.message = message;
    }

    /**
     * Returns true if a Broadcast does not already exist and can be created
     // @param mq the message to be sent in the broadcast
     * @param user the user who will be sending the broadcast
     * @param event the event whose attendees the broadcast will be sent to
     */
    public boolean canCreateNewBroadCast(User user, Event event) {
        // TODO: is there a better way to do this so it's not repeated:
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        Broadcast b = new Broadcast(broadcasters, event.getEventId());
        if (broadcasts.contains(b)) {
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
     // @param mq the message to be sent in the broadcast
     * @param user the user who will be sending the broadcast
     * @param event the event whose attendees the broadcast will be sent to
     */
    public Broadcast createNewBroadcast(User user, Event event) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        if(canCreateNewBroadCast(user, event)){
            Broadcast b = new Broadcast(broadcasters, event.getEventId());
            broadcasts.add(b);
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
        Broadcast b = createNewBroadcast(user, event);
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
        Broadcast broadcast = new Broadcast(broadcasters, event.getEventId());
        for(Conversation c: broadcasts){
            if(c.equals(broadcast)); // if chatroom already exists
            c.sendMessage(message.getContent(), user.get_userID());
        }
    }

    /**
     * Sends a Broadcast for multiple talks of a speaker
     * @param speaker the broadcast is being sent to all talks this speaker is speaking at
    // @param messageQueue messages being sent in the broadcast
     */
    public void createBroadcastInAllSpeakerEvents(Speaker speaker){ // mq removed
        for(int eventID: speaker.getTalksList()){
            createNewBroadcast(speaker, eventManager.getEvent(eventID));
        }
    }

    /**
     * Returns all broadcasts for a given userID
     * @param userID identifies user given this userID and returns the Broadcasts they can read
     */
    public ArrayList<Broadcast> returnBroadcastsforUserID(int userID){
        ArrayList<Broadcast> myBroadcasts = new ArrayList<>();
        for(Broadcast c: broadcasts){
            if (c.canRead(userID)){
                myBroadcasts.add(c);
            }
        }
        return myBroadcasts;
    }

}
