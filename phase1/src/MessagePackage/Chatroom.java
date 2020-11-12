package MessagePackage;

import java.util.ArrayList;

public class Chatroom implements Conversation{

    ArrayList<Integer> userList;
    MessageQueue messageQueue;
    enum status {ACCEPTED, PENDING, REJECTED}
    status myStatus;

    /**
     * Creates a chatroom between two or more users in userList
     * @param userList a list of all users one can message
     */
    public Chatroom(ArrayList<Integer> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    @Override
    public void sendMessage(String messageStr, int senderUserID){
        if(this.myStatus == status.ACCEPTED){
            Message newMessage = new Message(messageStr, senderUserID);
            this.messageQueue.pushMessage(newMessage);
        }
    }

    @Override
    public ArrayList<Message> readMessages(){
        return this.messageQueue.getMessages();
    }

    @Override
    public ArrayList<Integer> getAllReaderIDs(){
        return this.userList;
    }

    @Override
    public ArrayList<Integer> getAllSenderIDs(){
        return this.userList;
    }

    @Override
    public boolean canRead(Integer userID){
        return this.getAllReaderIDs().contains(userID);
    }

    @Override
    public boolean canSend(Integer userID){
        return this.getAllSenderIDs().contains(userID);
    }

    /**
     * Message request is accepted by recipient; users can freely message
     */
    public void acceptChatroom(){
        this.myStatus = status.ACCEPTED;
    }

    /**
     * Message request is rejected by recipient; no further messages can be sent
     */
    public void rejectChatroom(){
        this.myStatus = status.REJECTED;
    }

    /**
     * Message request is pending confirmation by recipient
     */
    public void pendingChatroom(){
        if(this.myStatus != status.ACCEPTED && this.myStatus != status.REJECTED){
            this.myStatus = status.PENDING;
        }
    }



}
