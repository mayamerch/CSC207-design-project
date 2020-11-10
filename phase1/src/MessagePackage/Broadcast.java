package MessagePackage;

import java.util.ArrayList;

import EventPackage.EventManager;
import EventPackage.EventManager.*;

public class Broadcast implements Conversation{
    // Speakers should be able to send a message that automatically goes to all Attendees of their talk/multiple talks they gave
    // Organizers should be able to send a message to all speakers or all Attendees

    public ArrayList<Integer> broadcasters;
    public MessageQueue messageQueue;
    public int eventID;

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

    @Override
    public void sendMessage(String messageStr, int senderUserID) {
        Message newMessage = new Message(messageStr, senderUserID);
        if(broadcasters.contains(senderUserID)){
            //for (String userID: getEvent(int eventID).getEventAttendees()){
                this.messageQueue.pushMessage(newMessage);
          //  }
        }
    }

    @Override
    public ArrayList<Message> readMessages() {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllReaderIDs() {
        // return attendees of an event with eventID
        return null;
    }

    @Override
    public ArrayList<Integer> getAllSenderIDs() {
        return broadcasters;
    }


}
