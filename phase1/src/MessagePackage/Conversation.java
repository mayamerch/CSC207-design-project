package MessagePackage;

import java.util.ArrayList;

public interface Conversation {

    /**
     *
     * @param messageStr a String for the content of the new Message to add to the Conversation
     * @param senderUserID the userID of the sender of the message
     */
    public void sendMessage(String messageStr, int senderUserID);

    /**
     *
     * @return an ArrayList of Messages in this conversation
     */
    public ArrayList<Message> readMessages();

    /**
     *
     * @return an ArrayList of users who can read messages from this Conversation.
     */
    public ArrayList<Integer> getAllReaderIDs();

    /**
     *
     * @return an ArrayList of users who can send messages to this Conversation.
     */
    public ArrayList<Integer> getAllSenderIDs();

}
