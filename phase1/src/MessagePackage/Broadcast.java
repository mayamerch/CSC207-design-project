package MessagePackage;

import java.util.ArrayList;

public class Broadcast implements Conversation{
    // Speakers should be able to send a message that automatically goes to all Attendees of their talk/multiple talks they gave
    // Organizers should be able to send a message to all speakers or all Attendees

    public ArrayList<String> receipients; // all users receiving the broadcast

    public Broadcast(Message m){

    }

    public void sendMessage(Message m){

    }

    @Override
    public void sendMessage(String messageStr, int senderUserID) {
        return null;
    }

    @Override
    public ArrayList<Message> readMessages() {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllReaderIDs() {
        return null;
    }

    @Override
    public ArrayList<Integer> getAllSenderIDs() {
        return null;
    }


}
