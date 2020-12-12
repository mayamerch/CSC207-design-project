package MessagePackage;

import EventPackage.EventEntities.Event;
import EventPackage.EventUseCases.EventManager;
import UserPackage.Speaker;
import UserPackage.User;
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
        this.gateway = new BroadcastGateway();
        this.broadcasts = gateway.getBroadcasts();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Save broadcasts to BroadcastDataFile. Should be run before program exits.
     */
    public void saveBroadcasts() {
        this.gateway.saveBroadcastsObject(this.broadcasts);
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
        Broadcast b = new Broadcast(broadcasters, eventID, eventManager, userManager);
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
            Broadcast b = new Broadcast(broadcasters, eventID, eventManager, userManager);
            broadcasts.add(b);
            return b;
        }
        else {
            throw new Error("This broadcast cannot be created.");
        }
    }

    /**
     * Sends a message in an existing Broadcast to an Event, or creates a new one if it doesn't exist
     * @param senderUserID the ID of the user who is sending the broadcast
     * @param eventID the ID of the event at which all the attendees are receiving the broadcast
     * @param message the message being broadcasted to the Event
     */
    public String sendBroadcastToEvent(int senderUserID, int eventID, String message){
        try {
            if(!eventManager.getEventList().contains(eventManager.getEvent(eventID))){
                return "This Event doesn't exist yet.";
            }

            if(userManager.getUserByID(senderUserID).getType() == UserType.ATTENDEE){
                return "Attendees cannot send Broadcasts";
            }

            ArrayList<Integer> broadcasters = new ArrayList<Integer>();
            broadcasters.add(senderUserID);
            Broadcast b = createNewBroadcast(senderUserID, eventID);

            for(Broadcast broadcast: broadcasts){
                if(broadcast.equals(b)){
                    broadcast.sendMessage(message, senderUserID);
                    return "Broadcast sent to Event " + eventID;
                }
            }
            b.sendMessage(message, senderUserID);
            broadcasts.add(b);
            return "Broadcast sent to Event " + eventID;

        }
        catch(ArrayIndexOutOfBoundsException exception){
            return "This Event doesn't exist yet.";
        }
    }


    /**
     * Sends a message in an existing Broadcast to all Attendees, or creates a new one if it doesn't exist
     * @param organizerUserID the ID of the user who is sending the broadcast
     * @param message the message being broadcasted to all Attendees
     */
    public String sendBroadcastToAttendees(int organizerUserID, String message){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        if(!(userManager.getUserByID(organizerUserID).getType() == UserType.ORGANIZER)){
            return "Only Organizers can Broadcast to Attendees.";
        }
        else{
            broadcasters.add(organizerUserID);
        }
        if(userManager.getAttendeeList().size() == 0){
            return "There are no Attendees!";
        }
        ArrayList<Integer> attendees = new ArrayList<Integer>();
        for(User user: userManager.getAttendeeList()){
            attendees.add(user.getUserID());
        }

        Broadcast b = new Broadcast(broadcasters, attendees, userManager);
        for(Broadcast broadcast: broadcasts){
            if(broadcast.equals(b)){
                broadcast.sendMessage(message, organizerUserID);
                return "Your broadcast has been sent!";
            }
        }
        b.sendMessage(message, organizerUserID);
        broadcasts.add(b);
        return "Your broadcast has been sent!";
    }

    /**
     * Sends a message in an existing Broadcast to all Speakers, or creates a new one if it doesn't exist
     * @param organizerUserID the ID of the user who is sending the broadcast
     * @param message the message being broadcasted to all Speakers
     */
    public String sendBroadcastToSpeakers(int organizerUserID, String message){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        if(!(userManager.getUserByID(organizerUserID).getType() == UserType.ORGANIZER)){
            return "Only Organizers can Broadcast to Speakers";
        }
        else{
            broadcasters.add(organizerUserID);
        }

        if(userManager.getSpeakerList().size() == 0){
            return "There are no Speakers!";
        }
        ArrayList<Integer> speakers = new ArrayList<Integer>();
        for(User user: userManager.getSpeakerList()){
            speakers.add(user.getUserID());
        }

        Broadcast b = new Broadcast(broadcasters, speakers, userManager);
        for(Broadcast broadcast: broadcasts){
            if(broadcast.equals(b)){
                broadcast.sendMessage(message, organizerUserID);
                return "Your broadcast has been sent!";
            }
        }
        b.sendMessage(message, organizerUserID);
        broadcasts.add(b);
        return "Your broadcast has been sent!";
    }

    /**
     * Sends a Broadcast for multiple talks of a speaker
     * @param speaker the broadcast is being sent to all talks this speaker is speaking at
     */
    public String sendBroadcastInAllSpeakerEvents(Speaker speaker, String message) {
        if((speaker.getType() != UserType.SPEAKER)){
            return "Only Speakers can do this!";
        }
        else if(speaker.getTalksList().size() == 0){
            return "You are not speaking at any events!";
        }
        else {
            for(int eventID: speaker.getTalksList()){
                sendBroadcastToEvent(speaker.getUserID(), eventID, message);
            }
        }
        return "Broadcast sent to all your events.";
    }

    /**
     * Sends a Broadcast for multiple talks of a speaker
     * @param eventID the ID of the event whose Broadcasts you are looking for
     */
    public String returnBroadcastforEventID(int eventID) {
        if(broadcasts.size() == 0){
            return "No Broadcasts for Event " + eventID + "!\n";
        }
        for(Broadcast b: broadcasts){
            if(b.getEventID() == eventID){
                return b.format();
            }
        }
        return "No Broadcasts for Event " + eventID + "!\n";
    }

    /**
     * Returns all broadcasts for a given userID
     * @param userID identifies user given this userID and returns the Broadcasts they can read
     */
    public ArrayList<Broadcast> returnBroadcastsforUserID(int userID){
        ArrayList<Broadcast> myBroadcasts = new ArrayList<>();
        if(broadcasts.size() == 0){
            return myBroadcasts;
        }
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
        if (returnBroadcastsforUserID(userID).size() == 0) {
            return "You have no broadcasts!";
        }
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