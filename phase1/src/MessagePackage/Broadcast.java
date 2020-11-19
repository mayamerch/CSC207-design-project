package MessagePackage;

import java.util.ArrayList;

import EventPackage.*;

public class Broadcast implements Conversation{
    // Speakers should be able to send a message that automatically goes to all Attendees of their talk/multiple talks they gave
    // Organizers should be able to send a message to all speakers or all Attendees

    private ArrayList<Integer> broadcasters;
    private MessageQueue messageQueue;
    private int eventID;
    //private Event e;  //TODO: Event e stored inside Broadcast but is also stored in EventManager?
    private EventManager eventManager;

    /**
     * Message broadcasted by someone in ArrayList broadcasters, identified by userID
     * @param broadcasters a list of userIDs of every Organizer or Speaker able to broadcast
     * @param eventID the ID of the event of which the attendees are being broadcasted to
     */
    public Broadcast(ArrayList<Integer> broadcasters, int eventID, EventManager eventManager){ // Event e){
        this.broadcasters = broadcasters;
        this.messageQueue = new MessageQueue();
        //this.e = e;
        this.eventID = eventID;
        this.eventManager = eventManager;
    }

    public MessageQueue getMessageQueue(){return messageQueue;}

    public int getEventID(){return eventID;}

    @Override
    public void sendMessage(String messageStr, int senderUserID) {
        Message newMessage = new Message(messageStr, senderUserID);
        if(broadcasters.contains(senderUserID)){
            this.messageQueue.pushMessage(newMessage);
        }
    }

    @Override
    public ArrayList<Message> readMessages() {
        return this.messageQueue.getMessages();
    }

    @Override
    public ArrayList<Integer> getAllReaderIDs() {
        return eventManager.getEvent(this.eventID).getEventAttendees(); /// e.getEventAttendees();
    }

    @Override
    public ArrayList<Integer> getAllSenderIDs() {
        return broadcasters;
    }

    @Override
    public boolean canRead(Integer userID){
        return this.getAllReaderIDs().contains(userID);
    }

    @Override
    public boolean canSend(Integer userID){
        return this.getAllSenderIDs().contains(userID);
    }


    /*public String toString(){
        return ("For " + e.getEventName() + ", from user " + this.broadcasters + ":\n" + this.messageQueue.toString());
    }*/

    /**
     * To use in Gateway class for saving Broadcast as a string to write to file.
     * @return string of all instance variables inside Broadcast
     */
    @Override
    public String toString(){
        return broadcasters.toString() + "\n" + messageQueue.toString() + "\n" + eventID;
    }

    /**
     * To use in Presenter for printing to console
     * @return string formatted for text UI for Broadcast
     */

    public String format(){
        return eventManager.getEvent(eventID).getEventName() + ":\n" + messageQueue.format();
    }
}
