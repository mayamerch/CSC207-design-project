package MessagePackage;

import java.util.ArrayList;

public class Chatroom implements Conversation{

    private ArrayList<Integer> userList;
    private MessageQueue messageQueue;
    //enum status {ACCEPTED, PENDING, REJECTED}
    //private status myStatus;

    /**
     * Creates a new chatroom between two or more users in userList
     * @param userList a list of all users one can message
     */
    public Chatroom(ArrayList<Integer> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    /**
     * Constructor used in Gateway to instantiate an existing chatroom with all of its data
     * @param userList a list of all users one can message
     * @param messageQueue a MessageQueue of all messages sent in chat
     */
    public Chatroom(ArrayList<Integer> userList, MessageQueue messageQueue){
        this.userList = userList;
        this.messageQueue = messageQueue;

    }

    public MessageQueue getMessageQueue(){return messageQueue;}


    @Override
    public void sendMessage(String messageStr, int senderUserID){
        Message newMessage = new Message(messageStr, senderUserID);
        this.messageQueue.pushMessage(newMessage);
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



    @Override
    public String toString(){
        return (this.userList + "\n" + this.messageQueue.toString());
    }


}
