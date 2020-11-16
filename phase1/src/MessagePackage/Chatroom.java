package MessagePackage;

import java.util.ArrayList;

public class Chatroom implements Conversation{

    private ArrayList<Integer> userList;
    private MessageQueue messageQueue;
    enum status {ACCEPTED, PENDING, REJECTED}
    private status myStatus;

    /**
     * Creates a new chatroom between two or more users in userList
     * @param userList a list of all users one can message
     */
    public Chatroom(ArrayList<Integer> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
        this.myStatus = status.PENDING;
    }

    /**
     * Constructor used in Gateway to instantiate an existing chatroom with all of its data
     * @param userList a list of all users one can message
     * @param messageQueue a MessageQueue of all messages sent in chat
     * @param myStatusStr a string matching "ACCEPTED" "PENDING" "REJECTED" to represent status enum
     */
    public Chatroom(ArrayList<Integer> userList, MessageQueue messageQueue, String myStatusStr){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
        this.myStatus = status.valueOf(myStatusStr);
    }

    public MessageQueue getMessageQueue(){return messageQueue;}


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
     *
     * @return myStatus whether or not the chatroom is ACCEPTED, PENDING or REJECTED
     */
    public status getMyStatus() {
        return myStatus;
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

    @Override
    public String toString(){
        return (this.myStatus.toString() + "\n" + this.userList + "\n" + this.messageQueue.toString());
    }



}
