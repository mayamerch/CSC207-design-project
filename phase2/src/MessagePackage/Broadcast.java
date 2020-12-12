package MessagePackage;

import EventPackage.EventUseCases.EventManager;
import UserPackage.Speaker;
import UserPackage.User;
import UserPackage.UserManager;
import UserPackage.UserType;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.ArrayList;

public class Broadcast implements Conversation, Serializable {

    private ArrayList<Integer> broadcasters;
    private int eventID;
    private EventManager eventManager;
    private ArrayList<Message> messages = new ArrayList<>();
    private UserManager userManager;
    private int senderID;
    private ArrayList<Integer> recipients;

    /**
     * Create a broadcast by someone in ArrayList broadcasters, identified by userID
     * @param broadcasters a list of userIDs of every Organizer or Speaker able to broadcast
     * @param eventID the ID of the event of which the attendees are being broadcasted to
     * @param eventManager an eventManager to manage the event that is being broadcasted to
     */
    public Broadcast(ArrayList<Integer> broadcasters, int eventID, EventManager eventManager, UserManager userManager){
        this.broadcasters = broadcasters;
        this.messages = new ArrayList<Message>();
        this.eventID = eventID;
        this.eventManager = eventManager;
        this.userManager = userManager;

        for(User organizer: userManager.getOrganizerList()){
            if(!broadcasters.contains(organizer.getUserID())){
                this.broadcasters.add(organizer.getUserID());
            }
        }
        for(User speaker: userManager.getSpeakerList()){
            if(!broadcasters.contains(speaker.getUserID())){
                this.broadcasters.add(speaker.getUserID());
            }
        }
    }

    /**
     * Create a broadcast from an Organizer to a specific group of people (Speakers or Attendees), rather than to an Event
     * @param broadcasters a list of userIDs of every Organizer or Speaker able to broadcast
     * @param recipients a list of all Users being broadcasted to
     */
    public Broadcast(ArrayList<Integer> broadcasters, ArrayList<Integer> recipients, UserManager userManager){
        this.recipients = recipients;
        this.userManager = userManager;
        this.broadcasters = broadcasters;
    }

    /**
     * Create a broadcast from existing saved broadcast (from BroadcastDataFile)
     * @param broadcasters a list of userIDs of every Organizer or Speaker able to broadcast
     * @param messages an ArrayList of Messages sent in this broadcast
     * @param eventID the ID of the event of which the attendees are being broadcasted to
     * @param eventManager an eventManager to manage the event that is being broadcasted to
     */
    public Broadcast(ArrayList<Integer> broadcasters, ArrayList<Message> messages, int eventID, EventManager eventManager){
        this.broadcasters = broadcasters;
        this.messages = messages;
        this.eventID = eventID;
        this.eventManager = eventManager;
    }

    /**
     * @return eventID instance variable
     */
    public int getEventID(){return eventID;}

    @Override
    public void sendMessage(String messageStr, int senderUserID) {
        User sender = userManager.getUserByID(senderUserID);
        if(broadcasters == null  || broadcasters.size() == 0){
            throw new Error("You are not able to Broadcast!");
        }
        if(!broadcasters.contains(senderUserID)){
            if(sender.getType() == UserType.ORGANIZER || sender.getType() == UserType.SPEAKER){
                broadcasters.add(senderUserID);
                this.messages.add(new Message(messageStr, senderUserID));
            }
        }
        else{
            this.messages.add(new Message(messageStr, senderUserID));
        }
    }

    /**
     * @return the ArrayList of Messages in this broadcast
     */
    @Override
    public ArrayList<Message> readMessages() {
        return this.messages;
    }

    /**
     * @return the ArrayList of userIDs who are attending the Event that is being Broadcasted to
     */
    @Override
    public ArrayList<Integer> getAllReaderIDs() {
        return eventManager.getEvent(this.eventID).getEventAttendees(); /// e.getEventAttendees();
    }

    /**
     * @return the ArrayList of userIDs who are able to send a Broadcast (Organizers, Speakers to their Events)
     */
    @Override
    public ArrayList<Integer> getAllSenderIDs() {
        return broadcasters;
    }

    /**
     * @return whether or not a user can see this Broadcast
     */
    @Override
    public boolean canRead(Integer userID){
        return this.getAllReaderIDs().contains(userID);
    }

    /**
     * @return whether or not a user can send this Broadcast
     */
    @Override
    public boolean canSend(Integer userID){
        return this.getAllSenderIDs().contains(userID);
    }

    /**
     * To use in Gateway class for saving Broadcast as a string to write to file.
     * @return string of all instance variables inside Broadcast
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        for(Message m : this.messages){
            s.append(m.toString()).append("\t");
        }
        String str = s.toString().trim() + "]";
        return broadcasters.toString() + "\n" + str + "\n" + eventID;
    }

    /**
     * To use in Presenter for printing to console
     * @return string formatted for text UI for Broadcast
     */
    public String format(){
        StringBuilder s = new StringBuilder();
        for(Message m : this.messages){
            s.append(m.format());
            s.append("\n");
        }
        return eventManager.getEvent(eventID).getEventName() + ":\n" + s.toString();
    }

}
