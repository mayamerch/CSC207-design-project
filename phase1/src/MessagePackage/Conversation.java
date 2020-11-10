package MessagePackage;

import java.util.ArrayList;

public interface Conversation {

    /**
     * User with ID senderUserID sends message with content messageStr
     * @param messageStr a String for the content of the new Message to add to the Conversation
     * @param senderUserID the userID of the sender of the message
     */
    void sendMessage(String messageStr, int senderUserID);

    /**
     * @return an ArrayList of unread Messages in this conversation
     * TODO: these are only new messages right?
     */
    ArrayList<Message> readMessages();

    /**
     * @return an ArrayList of users who can read messages from this Conversation.
     */
    ArrayList<Integer> getAllReaderIDs();

    /**
     * @return an ArrayList of users who can send messages to this Conversation.
     */
    ArrayList<Integer> getAllSenderIDs();

}
