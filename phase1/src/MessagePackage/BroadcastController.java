package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;
import UserPackage.User;

import java.util.ArrayList;

public class BroadcastController {
    private ArrayList<Broadcast> broadcasts;
    private EventManager eventManager;

    /**
     * Creates an instance of BroadcastController that contains all the recorded conversations (empty at first)
     */
    public BroadcastController(){
        this.broadcasts = new ArrayList<Broadcast>();
    }

    public ArrayList<Broadcast> getBroadcasts() {
        return broadcasts;
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
    public void sendNewBroadcast(User user, Event event, String message){
        Broadcast b = createNewBroadcast(user, event);
        if(broadcasts.contains(b)){
            sendExistingBroadcast(user, event, message);
        }
        else{
            b.sendMessage(message, user.get_userID());
        }
    }

    /**
     * Sends a message in an existing Broadcast
     * @param user the user who is sending the broadcast
     * @param event the event at which all the attendees are receiving the broadcast
     */
    public void sendExistingBroadcast(User user, Event event, String message){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(user.get_userID());
        Broadcast b = new Broadcast(broadcasters, event.getEventId());

        if(broadcasts.contains(b)){
            b.sendMessage(message, user.get_userID());
        }
        else{
            sendNewBroadcast(user, event, message);
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

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("");
        for (Broadcast b: this.broadcasts){
            s.append(b.toString());
            s.append("\n\n");
        }
        return s.toString();
    }

}
