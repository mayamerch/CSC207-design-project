package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;
import UserPackage.User;

import java.util.ArrayList;

public class BroadcastController {
    private ArrayList<Broadcast> broadcasts;
    private EventManager em;

    /**
     * Creates an instance of BroadcastController that contains all the recorded conversations (empty at first)
     */
    public BroadcastController(EventManager em){
        this.broadcasts = new ArrayList<Broadcast>();
        this.em = em;
    }

    public ArrayList<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    /**
     * Returns true if a Broadcast does not already exist and can be created
     // @param mq the message to be sent in the broadcast
     * @param senderUserID the user who will be sending the broadcast
     * @param eventID the ID of the event whose attendees the broadcast will be sent to
     */
    public boolean canCreateNewBroadCast(int senderUserID, int eventID) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(senderUserID);

        Broadcast b = new Broadcast(broadcasters, eventID, em);
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
     * @param senderUserID the user who will be sending the broadcast
     * @param eventID the ID of the event whose attendees the broadcast will be sent to
     */
    public Broadcast createNewBroadcast(int senderUserID, int eventID) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(senderUserID);

        if(canCreateNewBroadCast(senderUserID, eventID)){
            Broadcast b = new Broadcast(broadcasters, eventID, em);
            broadcasts.add(b);
            return b;
        }
        else {
            throw new java.lang.Error("This Broadcast cannot be created.");
        }
    }

    /**
     * Sends a message in an existing Broadcast, or creates a new one if it doesn't exist
     * @param senderUserID the ID of the user who is sending the broadcast
     * @param eventID the ID of the event at which all the attendees are receiving the broadcast
     */
    public void sendBroadcast(int senderUserID, int eventID, String message){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(senderUserID);
        Broadcast b = createNewBroadcast(senderUserID, eventID);

        for(Broadcast broadcast: broadcasts){
            if(broadcast.equals(b)){
                broadcast.sendMessage(message, senderUserID);
                System.out.println("Your broadcast has been sent.");
                return;
            }
        }
        b.sendMessage(message, senderUserID);
        broadcasts.add(b);
        System.out.println("Your broadcast has been sent.");
    }

    /**
     * Sends a Broadcast for multiple talks of a speaker
     * @param speaker the broadcast is being sent to all talks this speaker is speaking at
    // @param messageQueue messages being sent in the broadcast
     */
    public void createBroadcastInAllSpeakerEvents(Speaker speaker){ // mq removed
        for(int eventID: speaker.getTalksList()){
            createNewBroadcast(speaker.get_userID(), eventID);
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

    public String myBroadcasts(int userID){
        StringBuilder s = new StringBuilder("");
        for (Broadcast b: returnBroadcastsforUserID(userID)){
            s.append(b.format());
            s.append("\n------\n") ;
        }
        return s.toString();
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