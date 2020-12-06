package MessagePackage;

import EventPackage.EventEntities.Event;
import EventPackage.EventUseCases.EventManager;
import UserPackage.Speaker;
import UserPackage.UserManager;
import UserPackage.UserType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BroadcastController {
    private ArrayList<Broadcast> broadcasts;
    private EventManager eventManager;
    private UserManager userManager;
    private BroadcastGateway gateway;

    /**
     * Creates an instance of BroadcastController that contains all the recorded conversations (empty at first)
     */
    public BroadcastController(EventManager eventManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.gateway = new BroadcastGateway(eventManager);
        try {
            this.broadcasts = gateway.makeBroadcasts();
        } catch (FileNotFoundException f) {
            this.broadcasts = new ArrayList<Broadcast>();
        }

    }

    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Save broadcasts to BroadcastDataFile. Should be run before program exits.
     * @throws IOException if writing to file was unsuccessful
     */
    public void saveBroadcasts() throws IOException {
        this.gateway.writeBroadcastsToFile(this.broadcasts);
    }

    /**
     * @return an ArrayList of all broadcasts created
     */
    public ArrayList<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    /**
     * Returns true if a Broadcast does not already exist and can be created
     * @param senderUserID the user who will be sending the broadcast
     * @param eventID the ID of the event whose attendees the broadcast will be sent to
     */
    public boolean canCreateNewBroadCast(int senderUserID, int eventID) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        if(!(userManager.getUserByID(senderUserID).getType() == UserType.ORGANIZER ||
                userManager.getUserByID(senderUserID).getType() == UserType.SPEAKER)){
            throw new Error("You are not able to send a broadcast.");
        }
        else{
            broadcasters.add(senderUserID);
        }
        Broadcast b = new Broadcast(broadcasters, eventID, eventManager);
        return !broadcasts.contains(b); // return whether it exists or not
    }

    /**
     * Creates and returns a new Broadcast, if possible. Raises an Error if not.
     * @param senderUserID the user who will be sending the broadcast
     * @param eventID the ID of the event whose attendees the broadcast will be sent to
     */
    public Broadcast createNewBroadcast(int senderUserID, int eventID) {
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        broadcasters.add(senderUserID);

        if(canCreateNewBroadCast(senderUserID, eventID)){
            Broadcast b = new Broadcast(broadcasters, eventID, eventManager);
            broadcasts.add(b);
            return b;
        }
        else {
            throw new Error("This broadcast cannot be created.");
        }
    }

    /**
     * Sends a broadcast to everyone participating in the entire conference
     * @param organizerUserID the organizer who will be sending the broadcast
     * @param message the message to be broadcasted to the conference
     */
    // TODO: remove this method (changed to a chat)
    public void broadcastConference(int organizerUserID, String message){
        if(userManager.getUserByID(organizerUserID).getType() == UserType.ORGANIZER) {
            for (Event e : eventManager.getEventList()) {
                sendBroadcast(organizerUserID, e.getEventId(), message);
            }
        }
        else{
            throw new Error("Only organizers can broadcast to the entire conference.");
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

        if(!eventManager.getEventList().contains(eventManager.getEvent(eventID))){
            throw new Error("This event does not exist.");
        }

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
     */
    public void sendBroadcastInAllSpeakerEvents(Speaker speaker, String message){
        for(int eventID: speaker.getTalksList()){
            sendBroadcast(speaker.getUserID(), eventID, message);
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

    /**
     * To use in Presenter for printing to console
     * @param userID the broadcaster's userID
     * @return string formatted for text UI for Broadcast
     */
    public String myBroadcasts(int userID){
        StringBuilder s = new StringBuilder("");
        for (Broadcast b: returnBroadcastsforUserID(userID)){
            s.append(b.format());
            s.append("\n------\n") ;
        }
        return s.toString();
    }

    /**
     * @return string to be parsed by Gateway classes
     */
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