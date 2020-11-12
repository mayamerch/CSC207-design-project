package MessagePackage;

import java.util.ArrayList;

import EventPackage.*;

public class Broadcast implements Conversation{
    // Speakers should be able to send a message that automatically goes to all Attendees of their talk/multiple talks they gave
    // Organizers should be able to send a message to all speakers or all Attendees

    private ArrayList<Integer> broadcasters;
    private MessageQueue messageQueue;
    private int eventID;
    private EventManager eventManager;

    /**
     * Message broadcasted by someone in ArrayList broadcasters, identified by userID
     * @param messageQueue the Message being broadcasted
     * @param broadcasters a list of userIDs of every Organizer or Speaker able to broadcast
     * @param eventID the ID of the event of which the attendees are being broadcasted to
     */
    public Broadcast(MessageQueue messageQueue, ArrayList<Integer> broadcasters, int eventID){
        this.broadcasters = broadcasters;
        this.messageQueue = messageQueue;
        this.eventID = eventID;
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
        return eventManager.getEvent(this.eventID).getEventAttendees();
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

}
